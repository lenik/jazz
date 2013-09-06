package net.bodz.bas.c.javax.security.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.*;
import java.security.KeyStore.Builder;
import java.security.KeyStore.CallbackHandlerProtection;
import java.security.Provider.Service;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.java.net.CURL;
import net.bodz.bas.c.java.net.CURL.Alpha;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.iterator.PrefetchedIterator;

public class CertSelector
        implements II18nCapable {

    public static final int AUTO = 0;
    public static final int NONE = 1;
    public static final int STORETYPE = 2;
    public static final int KEYSTORE = 3;
    public static final int CERTIFICATE = 4;
    public static final int SUBENTRY = 5;

    private int type;
    private Provider provider;
    private KeyStore keyStore;

    private String storeType;
    private String storePassword;
    private String storePath;
    private File storeFile;
    private String certPassword;
    private String certAlias;
    private String subEntry; // not used.

    public CertSelector(String curl) {
        this(new CURL(curl));
    }

    public CertSelector(CURL curl) {
        this(curl, AUTO);
    }

    public CertSelector(String curl, Provider provider)
            throws KeyStoreException {
        this(new CURL(curl), provider);
    }

    public CertSelector(CURL curl, Provider provider)
            throws KeyStoreException {
        this(curl, AUTO, provider);
    }

    public CertSelector(CURL curl, int type) {
        try {
            _parse(curl, type, null);
        } catch (KeyStoreException e) {
            throw new UnexpectedException(e);
        }
    }

    public CertSelector(CURL curl, int type, Provider provider)
            throws KeyStoreException {
        _parse(curl, type, provider);
    }

    static final String defaultStoreType = "JKS";
    static final String KS_NONE = "-";

    void _parse(CURL curl, int type, Provider provider)
            throws KeyStoreException {
        if (curl == null)
            throw new NullPointerException("certURL");
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
                } else if (storePath.contains("://"))
                    try {
                        URL url = new URL(storePath);
                        storeFile = FileURL.toFile(url, null);
                        if (storeFile == null)
                            ; // create a temp file when necessary?
                    } catch (MalformedURLException e) {
                        throw new IllegalArgumentException(e);
                    }
                else {
                    storeFile = FilePath.canoniOf(storePath);
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
            throw new IllegalArgumentException(tr._("Type(%s) is not completed: %s", type, curl));
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
        class Iter
                extends PrefetchedIterator<Provider> {
            Provider[] all = Security.getProviders();
            int i = 0;

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
                            if ("KeyStore".equals(service.getType()))
                                return provider;
                        }
                    }
                }
                return end();
            }
        }
        return new Iterable<Provider>() {
            @Override
            public Iterator<Provider> iterator() {
                return new Iter();
            }
        };
    }

    @SuppressWarnings("restriction")
    protected CallbackHandler getPromptPasswordHandler() {
        return new com.sun.security.auth.callback.TextCallbackHandler();
    }

    public KeyStore getKeyStore()
            throws KeyStoreException {
        if (keyStore == null)
            keyStore = getKeyStore(provider);
        return keyStore;
    }

    public KeyStore getKeyStore(Provider provider)
            throws KeyStoreException {
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
                    in = new FileInputStream(storeFile);
                else
                    throw new IllegalStateException(tr._("No key store specified?"));
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

    public Certificate getCertificate()
            throws KeyStoreException {
        if (type < CERTIFICATE)
            throw new IllegalUsageException();
        KeyStore keyStore = getKeyStore();
        return getCertificate(keyStore);
    }

    public Certificate getCertificate(Provider provider)
            throws KeyStoreException {
        if (type < CERTIFICATE)
            throw new IllegalUsageException();
        KeyStore keyStore = getKeyStore(provider);
        return getCertificate(keyStore);
    }

    Certificate getCertificate(KeyStore keyStore)
            throws KeyStoreException {
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
        if (!Nullables.equals(storeType, c.storeType))
            return false;
        if (!Nullables.equals(storePassword, c.storePassword))
            return false;
        if (!Nullables.equals(storePath, c.storePath))
            return false;
        if (!Nullables.equals(certPassword, c.certPassword))
            return false;
        if (!Nullables.equals(certAlias, c.certAlias))
            return false;
        if (!Nullables.equals(subEntry, c.subEntry))
            return false;
        return true;
    }

    String getTypeString() {
        switch (type) {
        case AUTO:
            return "AUTO";
        case NONE:
            return "NONE";
        case STORETYPE:
            return "STORETYPE";
        case KEYSTORE:
            return "KEYSTORE";
        case CERTIFICATE:
            return "CERTIFICATE";
        case SUBENTRY:
            return "SUBENTRY";
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
                String s = certAlias == null ? "" : certAlias;
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

    public void dump(IPrintOut out)
            throws KeyStoreException {
        dump(out, 1);
    }

    public void dump(IPrintOut out, int detail)
            throws KeyStoreException {
        if (type == NONE) {
            out.println("NONE");
            return;
        }
        PKIDumper dumper = new PKIDumper(out, detail);
        KeyStore keyStore;
        switch (type) {
        case STORETYPE:
            if (provider == null) {
                // dump all providers
                for (Provider provider : getStoreTypeProviders()) {
                    out.println("Provider " + provider.getName());
                    keyStore = getKeyStore(provider);
                    dumper.dumpKeyStore("", keyStore);
                }
            } else {
                keyStore = getKeyStore();
                // dump keystore of specified provider
                dumper.dumpKeyStore("", keyStore);
            }
            break;
        case KEYSTORE:
            keyStore = getKeyStore();
            dumper.dumpKeyStore("", keyStore);
            break;
        case CERTIFICATE:
            keyStore = getKeyStore();
            dumper.dumpStoreEntry("", keyStore, certAlias, certPassword);
            break;
        case SUBENTRY:
            out.println("Not implemented.");
            break;
        default:
            throw new IllegalStateException();
        }
        out.flush();
    }

}
