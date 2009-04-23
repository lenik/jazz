package net.bodz.bas.sec.pki.util;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import java.security.Security;
import java.security.KeyStore.Builder;
import java.security.KeyStore.CallbackHandlerProtection;
import java.security.Provider.Service;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.net.CURL;
import net.bodz.bas.net.CURL.Alpha;
import net.bodz.bas.nls.SysNLS;
import net.bodz.bas.types.util.Iterates;
import net.bodz.bas.types.util.Objects;
import net.bodz.bas.types.util.PrefetchedIterator;

import com.sun.security.auth.callback.TextCallbackHandler;

/**
 * @test CertSelectorTest
 */
public class CertSelector {

    public static final int AUTO        = 0;
    public static final int NONE        = 1;
    public static final int STORETYPE   = 2;
    public static final int KEYSTORE    = 3;
    public static final int CERTIFICATE = 4;
    public static final int SUBENTRY    = 5;

    private int             type;
    private Provider        provider;
    private KeyStore        keyStore;

    private String          storeType;
    private String          storePassword;
    private String          storePath;
    private File            storeFile;
    private String          certPassword;
    private String          certAlias;
    private String          subEntry;       // not used.

    public CertSelector(String curl) {
        this(new CURL(curl));
    }

    public CertSelector(CURL curl) {
        this(curl, AUTO);
    }

    public CertSelector(String curl, Provider provider) throws KeyStoreException {
        this(new CURL(curl), provider);
    }

    public CertSelector(CURL curl, Provider provider) throws KeyStoreException {
        this(curl, AUTO, provider);
    }

    public CertSelector(CURL curl, int type) {
        try {
            _parse(curl, type, null);
        } catch (KeyStoreException e) {
            throw new UnexpectedException(e);
        }
    }

    public CertSelector(CURL curl, int type, Provider provider) throws KeyStoreException {
        _parse(curl, type, provider);
    }

    static final String defaultStoreType = "JKS"; //$NON-NLS-1$
    static final String KS_NONE          = "-";  //$NON-NLS-1$

    void _parse(CURL curl, int type, Provider provider) throws KeyStoreException {
        if (curl == null)
            throw new NullPointerException("certURL"); //$NON-NLS-1$
        int detType = NONE;
        storeType = curl.getType();
        if (storeType != null)
            detType = STORETYPE;
        else
            storeType = defaultStoreType;
        Alpha[] alphas = curl.getAlphas();
        if (alphas.length >= 1) {
            Alpha ks = alphas[0];
            String[] storeParams = ks.getInitParameters();
            storePath = ks.formatBetas();
            if (storeParams != null)
                storePassword = storeParams[0];
            if (!storePath.isEmpty()) {
                if (KS_NONE.equals(storePath)) {
                    storeFile = null;
                } else if (storePath.contains("://")) //$NON-NLS-1$
                    try {
                        URL url = new URL(storePath);
                        // create a temp file when necessary?
                        storeFile = new File(url.toURI());
                    } catch (URISyntaxException e) {
                        throw new IllegalArgumentException(e);
                    } catch (MalformedURLException e) {
                        throw new IllegalArgumentException(e);
                    }
                else {
                    storeFile = Files.canoniOf(storePath);
                }
            }
            detType = KEYSTORE;
            if (alphas.length >= 2) {
                Alpha cert = alphas[1];
                String[] certParams = cert.getInitParameters();
                String alias = cert.formatBetas();
                if (certParams != null)
                    certPassword = certParams[0];
                if (alias != null) {
                    certAlias = alias;
                    detType = CERTIFICATE;
                }
                if (alphas.length >= 3) {
                    Alpha subent = alphas[2];
                    subEntry = subent.formatBetas();
                    detType = SUBENTRY;
                }
            }
        }
        if (type > detType)
            throw new IllegalArgumentException(String.format(SysNLS
                    .getString("CertSelector.typeIsntCompleted_ss"), type, curl)); //$NON-NLS-1$
        this.type = type == AUTO ? detType : type;
        if (provider != null) {
            this.provider = provider;
            if (type >= KEYSTORE)
                this.keyStore = getKeyStore();
        }
    }

    public int getType() {
        return type;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getStoreType() {
        return storeType;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public String getStorePath() {
        return storePath;
    }

    public File getStoreFile() {
        return storeFile;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public String getCertAlias() {
        return certAlias;
    }

    public String getSubEntry() {
        return subEntry;
    }

    public Iterable<Provider> getStoreTypeProviders() {
        class Iter extends PrefetchedIterator<Provider> {
            Provider[] all = Security.getProviders();
            int        i   = 0;

            @Override
            protected Provider fetch() {
                while (i < all.length) {
                    Provider provider = all[i++];
                    for (Service service : provider.getServices()) {
                        // if (!"KeyStore".equals(service.getType()))
                        // continue;
                        if (type >= STORETYPE) {
                            if (storeType.equals(service.getAlgorithm()))
                                return provider;
                        } else {
                            if ("KeyStore".equals(service.getType())) //$NON-NLS-1$
                                return provider;
                        }
                    }
                }
                return end();
            }
        }
        return Iterates.iterate(Iter.class, this);
    }

    protected CallbackHandler getPromptPasswordHandler() {
        return new TextCallbackHandler();
    }

    public KeyStore getKeyStore() throws KeyStoreException {
        if (keyStore == null)
            keyStore = getKeyStore(provider);
        return keyStore;
    }

    public KeyStore getKeyStore(Provider provider) throws KeyStoreException {
        if (type < KEYSTORE)
            throw new IllegalUsageException();
        KeyStore keyStore;
        if (storePassword != null) {
            if (provider == null)
                keyStore = KeyStore.getInstance(storeType);
            else
                keyStore = KeyStore.getInstance(storeType, provider);
            try {
                InputStream in = null;
                if (storeFile != null)
                    in = Files.getInputStream(storeFile);
                else
                    throw new IllegalStateException(SysNLS.getString("CertSelector.noKeyStore")); //$NON-NLS-1$
                keyStore.load(in, storePassword.toCharArray());
            } catch (Exception e) {
                throw new KeyStoreException(e);
            }
        } else {
            CallbackHandler ch;
            if (storePassword == null)
                ch = getPromptPasswordHandler();
            else
                ch = new SimpleCallbackHandler(storePassword);
            CallbackHandlerProtection prot = new CallbackHandlerProtection(ch);
            File file = storeFile;
            Builder builder;
            if (file == null)
                builder = Builder.newInstance(storeType, provider, prot);
            else
                builder = Builder.newInstance(storeType, provider, file, prot);
            // will ask password here.
            keyStore = builder.getKeyStore();
        }
        return keyStore;
    }

    public Certificate getCertificate() throws KeyStoreException {
        if (type < CERTIFICATE)
            throw new IllegalUsageException();
        KeyStore keyStore = getKeyStore();
        return getCertificate(keyStore);
    }

    public Certificate getCertificate(Provider provider) throws KeyStoreException {
        if (type < CERTIFICATE)
            throw new IllegalUsageException();
        KeyStore keyStore = getKeyStore(provider);
        return getCertificate(keyStore);
    }

    Certificate getCertificate(KeyStore keyStore) throws KeyStoreException {
        Certificate cert = keyStore.getCertificate(certAlias);
        return cert;
    }

    @Override
    public int hashCode() {
        int h = type;
        if (storeType != null)
            h = h * 97 + storeType.hashCode();
        if (storePassword != null)
            h = h * 97 + storePassword.hashCode();
        if (storePath != null)
            h = h * 97 + storePath.hashCode();
        if (certPassword != null)
            h = h * 97 + certPassword.hashCode();
        if (certAlias != null)
            h = h * 97 + certAlias.hashCode();
        if (subEntry != null)
            h = h * 97 + subEntry.hashCode();
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CertSelector))
            return false;
        CertSelector c = (CertSelector) obj;
        if (type != c.type)
            return false;
        if (!Objects.equals(storeType, c.storeType))
            return false;
        if (!Objects.equals(storePassword, c.storePassword))
            return false;
        if (!Objects.equals(storePath, c.storePath))
            return false;
        if (!Objects.equals(certPassword, c.certPassword))
            return false;
        if (!Objects.equals(certAlias, c.certAlias))
            return false;
        if (!Objects.equals(subEntry, c.subEntry))
            return false;
        return true;
    }

    String getTypeString() {
        switch (type) {
        case AUTO:
            return "AUTO"; //$NON-NLS-1$
        case NONE:
            return "NONE"; //$NON-NLS-1$
        case STORETYPE:
            return "STORETYPE"; //$NON-NLS-1$
        case KEYSTORE:
            return "KEYSTORE"; //$NON-NLS-1$
        case CERTIFICATE:
            return "CERTIFICATE"; //$NON-NLS-1$
        case SUBENTRY:
            return "SUBENTRY"; //$NON-NLS-1$
        }
        throw new UnexpectedException();
    }

    public CURL toCURL() {
        List<Alpha> list = new ArrayList<Alpha>(3);
        if (storePath != null) {
            String[] params1 = null;
            if (storePassword != null)
                params1 = new String[] { storePassword };
            String[] betas1;
            betas1 = Alpha.parseBetas(storePath.toString());
            Alpha alpha1 = new Alpha(params1, betas1);
            list.add(alpha1);
            if (certAlias != null || certPassword != null) {
                String[] params2 = null;
                if (certPassword != null)
                    params2 = new String[] { certPassword };
                String s = certAlias == null ? "" : certAlias; //$NON-NLS-1$
                String[] betas2 = Alpha.parseBetas(s);
                Alpha alpha2 = new Alpha(params2, betas2);
                list.add(alpha2);
                if (subEntry != null) {
                    String[] betas3 = Alpha.parseBetas(subEntry);
                    Alpha alpha3 = new Alpha(null, betas3);
                    list.add(alpha3);
                }
            }
        }
        Alpha[] alphas = list.toArray(new Alpha[0]);
        return new CURL(storeType, alphas);
    }

    @Override
    public String toString() {
        return toCURL().toString();
    }

    public void dump(CharOut out) throws KeyStoreException {
        dump(out, 1);
    }

    public void dump(CharOut out, int detail) throws KeyStoreException {
        if (type == NONE) {
            out.println("NONE"); //$NON-NLS-1$
            return;
        }
        PKIDumper dumper = new PKIDumper(out, detail);
        KeyStore keyStore;
        switch (type) {
        case STORETYPE:
            if (provider == null) {
                // dump all providers
                for (Provider provider : getStoreTypeProviders()) {
                    out.println("Provider " + provider.getName()); //$NON-NLS-1$
                    keyStore = getKeyStore(provider);
                    dumper.dumpKeyStore("", keyStore); //$NON-NLS-1$
                }
            } else {
                keyStore = getKeyStore();
                // dump keystore of specified provider
                dumper.dumpKeyStore("", keyStore); //$NON-NLS-1$
            }
            break;
        case KEYSTORE:
            keyStore = getKeyStore();
            dumper.dumpKeyStore("", keyStore); //$NON-NLS-1$
            break;
        case CERTIFICATE:
            keyStore = getKeyStore();
            dumper.dumpStoreEntry("", keyStore, certAlias, certPassword); //$NON-NLS-1$
            break;
        case SUBENTRY:
            out.println("Not implemented."); //$NON-NLS-1$
            break;
        default:
            throw new IllegalStateException();
        }
        out.flush();
    }

}
