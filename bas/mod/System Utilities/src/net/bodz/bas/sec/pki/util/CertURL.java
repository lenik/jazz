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

import javax.security.auth.callback.CallbackHandler;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.util.Iterators;
import net.bodz.bas.types.util.Objects;
import net.bodz.bas.types.util.PrefetchedIterator;

import com.sun.security.auth.callback.TextCallbackHandler;

public class CertURL {

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
    private URL             storeURL;
    private File            storeURLFile;
    private String          certPassword;
    private String          certAlias;
    private String          subEntry;       // not used.

    public CertURL(String certURL) {
        this(certURL, AUTO);
    }

    public CertURL(String certURL, Provider provider) throws KeyStoreException {
        this(certURL, AUTO, provider);
    }

    public CertURL(String certURL, int type) {
        try {
            _parse(certURL, type, null);
        } catch (KeyStoreException e) {
            throw new UnexpectedException(e);
        }
    }

    public CertURL(String certURL, int type, Provider provider)
            throws KeyStoreException {
        _parse(certURL, type, provider);
    }

    void _parse(String certURL, int type, Provider provider)
            throws KeyStoreException {
        if (certURL == null)
            throw new NullPointerException("certURL");
        int detType;
        String s = certURL; // | ST://SP@URL#CP@ALIAS/...
        int p;
        p = s.indexOf("://");
        if (p != -1) {
            storeType = s.substring(0, p);
            s = s.substring(p + 3); // ST:// | SP@PATH#CP@ALIAS/...
            int _sharp = s.indexOf('#');
            p = s.indexOf('@'); // XXX - [#@] in the password
            if (_sharp != -1 && p > _sharp)
                p = -1;
            if (p != -1) {
                storePassword = s.substring(0, p);
                s = s.substring(p + 1); // ST://SP@ | PATH#CP@ALIAS/...
            }
            p = s.indexOf('#');
            String path;
            if (p != -1) {
                path = s.substring(0, p);
                s = s.substring(p + 1); // ST://SP@PATH# | CP@ALIAS/...
                p = s.indexOf('@');
                if (p != -1) {
                    certPassword = s.substring(0, p);
                    s = s.substring(p + 1); // ST://SP@URL#CP@ | ALIAS/...
                }
                p = s.indexOf('/');
                if (p != -1) {
                    certAlias = s.substring(0, p);
                    subEntry = s.substring(p + 1);
                    if (subEntry.isEmpty())
                        subEntry = null;
                    detType = SUBENTRY;
                } else {
                    certAlias = s;
                    detType = CERTIFICATE;
                }
                if (certAlias.isEmpty()) {
                    certAlias = null;
                    detType = KEYSTORE;
                }
            } else {
                path = s;
                detType = KEYSTORE;
            }
            if (path.contains("://"))
                try {
                    storeURL = new URL(path);
                    // create a temp file when necessary?
                    storeURLFile = new File(storeURL.toURI());
                } catch (URISyntaxException e) {
                    throw new IllegalArgumentException(e);
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException(e);
                }
            else {
                storeURLFile = Files.canoniOf(path);
                storeURL = Files.getURL(storeURLFile);
            }
        } else {
            storeType = s;
            detType = STORETYPE;
        }
        if (type > detType)
            throw new IllegalArgumentException("type(" + type
                    + ") not completed: " + certURL);
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

    public URL getStoreURL() {
        return storeURL;
    }

    public File getStoreURLFile() {
        return storeURLFile;
    }

    public String getAlias() {
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
            protected Object fetch() {
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
                return END;
            }
        }
        return Iterators.iterate(Iter.class, this);
    }

    protected CallbackHandler getPromptPasswordHandler() {
        return new TextCallbackHandler();
    }

    public KeyStore getKeyStore() throws KeyStoreException {
        return getKeyStore(provider);
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
                InputStream in = Files.getInputStream(storeURL);
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
            File file = storeURLFile;
            Builder builder = Builder.newInstance(storeType, provider, file,
                    prot);
            // will ask password here.
            keyStore = builder.getKeyStore();
        }
        return keyStore;
    }

    public Certificate getCertificate() throws KeyStoreException {
        if (type < CERTIFICATE)
            throw new IllegalUsageException();
        if (provider == null)
            keyStore = getKeyStore(); // ...
        return getCertificate(keyStore);
    }

    public Certificate getCertificate(Provider provider)
            throws KeyStoreException {
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
        if (storeURL != null)
            h = h * 97 + storeURL.hashCode();
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
        if (!(obj instanceof CertURL))
            return false;
        CertURL c = (CertURL) obj;
        if (type != c.type)
            return false;
        if (!Objects.equals(storeType, c.storeType))
            return false;
        if (!Objects.equals(storePassword, c.storePassword))
            return false;
        if (!Objects.equals(storeURL, c.storeURL))
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

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(128);
        if (type >= STORETYPE) {
            buf.append(storeType);
            if (type >= KEYSTORE) {
                buf.append("://");
                if (storePassword != null) {
                    buf.append(storePassword);
                    buf.append("@");
                }
                buf.append(storeURL);
                if (type >= CERTIFICATE) {
                    buf.append("#");
                    if (certPassword != null) {
                        buf.append(certPassword);
                        buf.append("@");
                    }
                    buf.append(certAlias);
                    if (type >= SUBENTRY) {
                        buf.append("/");
                        buf.append(subEntry);
                    }
                }
            }
        }
        return buf.toString();
    }

    public void dump(CharOut out) throws KeyStoreException {
        int det;
        switch (type) {
        case NONE:
        case KEYSTORE:
            det = CertDumper.SIMPLE;
            break;
        case CERTIFICATE:
            det = CertDumper.DETAIL;
            break;
        case SUBENTRY:
        default:
            det = CertDumper.FULL;
            break;
        }
        dump(out, det);
    }

    /**
     * @see CertDumper#SIMPLE
     * @see CertDumper#DETAIL
     * @see CertDumper#FULL
     */
    public void dump(CharOut out, int detail) throws KeyStoreException {
        if (provider == null) {
            for (Provider provider : getStoreTypeProviders()) {
                out.println("Provider " + provider.getName());
                KeyStore keyStore = getKeyStore(provider);
                dump(keyStore, out, detail);
            }
        } else
            dump(keyStore, out, detail);
    }

    void dump(KeyStore keyStore, CharOut out, int detail)
            throws KeyStoreException {
        CertDumper dumper = new CertDumper(out, detail);
        if (type >= CERTIFICATE) {
            assert certAlias != null;
            dumper.dump("", keyStore, certAlias, certPassword);
            Certificate[] chain = keyStore.getCertificateChain(certAlias);
            if (chain != null)
                for (Certificate cert : chain) {
                    out.print("> ");
                    dumper.dump("  ", cert);
                }
        } else {
            for (String alias : Iterators.iterate(keyStore.aliases())) {
                dumper.dump("", keyStore, alias, certPassword);
            }
        }
    }

}
