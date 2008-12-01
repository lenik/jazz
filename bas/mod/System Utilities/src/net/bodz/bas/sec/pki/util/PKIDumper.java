package net.bodz.bas.sec.pki.util;

import static net.bodz.bas.types.util.ArrayOps.Bytes;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.Provider;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStore.Builder;
import java.security.KeyStore.CallbackHandlerProtection;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.ProtectionParameter;
import java.security.Provider.Service;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.LDAPCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.ExemptionMechanism;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.x500.X500Principal;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.sec.crypto.Cryptos;
import net.bodz.bas.text.encodings.Encodings;
import net.bodz.bas.text.encodings.HexEncoding;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;
import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Iterates;
import net.bodz.bas.types.util.Strings;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Name;
import sun.security.x509.X509Key;

import com.sun.security.auth.callback.TextCallbackHandler;

public class PKIDumper {

    private CharOut out;
    private int     detail;

    public PKIDumper(CharOut out, int detail) {
        if (out == null)
            throw new NullPointerException("out");
        // if (out instanceof LogOut)
        this.out = out;
        // else
        // this.out = LogOuts.get(out,"pki-dumper");
        this.detail = detail;
    }

    private static class ServiceComparator implements Comparator<Service> {
        @Override
        public int compare(Service a, Service b) {
            int c = a.getType().compareTo(b.getType());
            if (c != 0)
                return c;
            c = a.getAlgorithm().compareTo(b.getAlgorithm());
            if (c != 0)
                return c;
            c = a.getClassName().compareTo(b.getClassName());
            return c;
        }
    }

    static final ServiceComparator serviceComp = new ServiceComparator();

    public void dumpProvider(String prefix, Provider provider, String password) {
        dumpProvider(prefix, provider, password, detail);
    }

    public void dumpProvider(String prefix, Provider provider, String password,
            int detail) {
        out.print(prefix);
        out.printf("Provider %s (%.2f): %s\n", provider.getName(), provider
                .getVersion(), provider.getInfo());
        if (detail >= 1)
            dumpProperties(prefix + "  ", provider.getName(), provider);

        List<Service> services = new ArrayList<Service>(provider.getServices());
        Collections.sort(services, serviceComp);
        TextMap<Set<Service>> types = new TreeTextMap<Set<Service>>();
        for (Service service : services) {
            String type = service.getType();
            String alg = service.getAlgorithm();
            out.printf(prefix, "  Service %s(%s): %s\n", type, alg, service
                    .getClassName());
            Set<Service> servsInType = types.get(type);
            if (servsInType == null)
                types.put(type, servsInType = new HashSet<Service>());
            servsInType.add(service);
        }

        Set<Service> certStores = types.get("CertStore");
        if (certStores != null)
            for (Service storeServ : certStores) {
                assert provider == storeServ.getProvider();
                String storeType = storeServ.getAlgorithm();
                out.print(prefix, "  CertStore ", storeType, ": ");
                if (detail >= 1) {
                    CertStore store;
                    CertStoreParameters params = null;
                    // if (password != null)
                    if ("Collection".equals(storeType)) {
                        params = new CollectionCertStoreParameters();
                    } else if ("com.sun.security.IndexedCollection"
                            .equals(storeType)) {
                        params = new CollectionCertStoreParameters();
                    } else if ("LDAP".equals(storeType)) {
                        params = new LDAPCertStoreParameters();
                    }
                    try {
                        store = CertStore.getInstance(storeType, params,
                                provider);
                        dumpCertStore(prefix + "  ", store, detail - 1);
                    } catch (Exception e) {
                        dumpError(prefix + "  ", e, detail);
                    }
                } else
                    out.println();
            }

        Set<Service> keyStores = types.get("KeyStore");
        if (keyStores != null)
            for (Service storeServ : keyStores) {
                assert provider == storeServ.getProvider();
                String storeType = storeServ.getAlgorithm();
                // String storeClass = storeServ.getClassName();
                out.print(prefix, "  KeyStore ", storeType, ": ");
                if (detail >= 1) {
                    KeyStore store = null;
                    try {
                        if (password != null) {
                            store = KeyStore.getInstance(storeType, provider);
                            store.load(null, password.toCharArray());
                        } else {
                            CallbackHandler ch = new TextCallbackHandler();
                            Builder builder = Builder
                                    .newInstance(storeType, provider,
                                            new CallbackHandlerProtection(ch));
                            // will ask password here.
                            store = builder.getKeyStore();
                        }
                    } catch (Exception e) {
                        dumpError(prefix + "  ", e, detail);
                    }
                    if (store != null) {
                        try {
                            out.println(store.size(), " entries");
                            for (String alias : Iterates.iterate(store
                                    .aliases())) {
                                dumpStoreEntry(prefix + "  ", store, alias,
                                        null, detail - 1);
                            }
                        } catch (KeyStoreException e) {
                            dumpError(prefix + "  ", e, detail);
                        }
                    }
                } else
                    out.println();
            }

        Set<Service> ciphers = types.get("Cipher");
        if (ciphers != null)
            for (Service cipherServ : ciphers) {
                assert provider == cipherServ.getProvider();
                String cipherAlg = cipherServ.getAlgorithm();
                // String cipherClass = cipherServ.getClassName();
                out.println("  Cipher ", cipherAlg, ": ");
                if (detail >= 1) {
                    Cipher cipher = null;
                    try {
                        cipher = Cipher.getInstance(cipherAlg, provider);
                        dumpCipher(cipher, detail - 1);
                    } catch (GeneralSecurityException e) {
                        dumpError(prefix + "  ", e, detail);
                    }
                }
            }

        out.println();
    }

    public void dumpCertStore(String prefix, CertStore store)
            throws CertStoreException {
        dumpCertStore(prefix, store, detail);
    }

    public void dumpCertStore(String prefix, CertStore store, int detail)
            throws CertStoreException {
        Collection<? extends Certificate> certs = store.getCertificates(null);
        out.println(certs.size(), " entries");
        if (detail <= 0)
            return;
        for (Certificate cert : certs) {
            dumpCert("    ", cert, detail);
        }
    }

    public void dumpKeyStore(String prefix, KeyStore keyStore)
            throws KeyStoreException {
        dumpKeyStore(prefix, keyStore, detail);
    }

    public void dumpKeyStore(String prefix, KeyStore keyStore, int detail)
            throws KeyStoreException {
        for (String alias : Iterates.iterate(keyStore.aliases()))
            dumpStoreEntry(prefix + "  ", keyStore, alias, null, detail);
    }

    public void dumpProperties(String prefix, String title,
            Properties properties) {
        out.println(prefix, title, " properties: ");
        List<Object> keys = new ArrayList<Object>(properties.keySet());
        Collections.sort(keys, Comparators.STD);
        for (Object key : keys) {
            Object value = properties.get(key);
            out.println(prefix, "  ", key, " = ", value);
        }
    }

    void dumpCert(String prefix, Certificate cert, int detail) {
        String certType = cert.getType();
        out.print(prefix + certType + ": ");
        if (cert instanceof X509Certificate)
            dumpX509Cert(prefix + "  ", (X509Certificate) cert, detail);
        else {
            out.println(prefix + "(value of " + cert.getClass() + ")");
            out.println(prefix + "  " + cert);
        }
    }

    public void dumpX509Cert(String prefix, X509Certificate x509, int detail) {
        Principal subjectDN = x509.getSubjectDN();
        if (subjectDN instanceof X500Name) {
            out.println(subjectDN);
        } else {
            out.println(subjectDN);
        }
        if (--detail < 0)
            return;

        int basicCons = x509.getBasicConstraints();
        out.printf(prefix + "basic-constraints: %d (%h)\n", basicCons,
                basicCons);

        Set<String> critSet = x509.getCriticalExtensionOIDs();
        if (critSet != null && !critSet.isEmpty()) {
            out.println(prefix + "critical-extensions: ");
            for (String oid : critSet)
                out.println(prefix + "  " + formatOID(oid));
        }

        try {
            byte[] encoded = x509.getEncoded();
            out.print(prefix + "encoded: ");
            dumpBytes(prefix + "  ", encoded, detail);
        } catch (CertificateEncodingException e) {
            dumpError(prefix + "  ", e, detail);
        }

        try {
            List<String> extendedKeyUsage = x509.getExtendedKeyUsage();
            if (extendedKeyUsage != null && !extendedKeyUsage.isEmpty()) {
                out.println(prefix + "extended-key-usage: ");
                for (String oid : extendedKeyUsage) {
                    byte[] val = x509.getExtensionValue(oid);
                    out.print(prefix + "  " + formatOID(oid) + " = ");
                    dumpBytes(prefix + "    ", val, detail);
                }
            }
        } catch (CertificateParsingException e) {
            dumpError(prefix + "  ", e, detail);
        }

        try {
            Collection<List<?>> issAltNames = x509.getIssuerAlternativeNames();
            if (issAltNames != null && !issAltNames.isEmpty()) {
                out.println(prefix + "issuer-alternative-names: ");
                _dumpAltNames(prefix + "  ", issAltNames);
            }
        } catch (CertificateParsingException e) {
            dumpError(prefix + "  ", e, detail);
        }

        // Principal issuerDN = x509.getIssuerDN();

        boolean[] issuerUniqueID = x509.getIssuerUniqueID();
        if (issuerUniqueID != null) {
            out.print(prefix + "issuer-unique-id: ");
            dumpBits(prefix + "  ", issuerUniqueID);
        }

        X500Principal issuer = x509.getIssuerX500Principal();
        if (issuer != null) {
            out.println(prefix + "issuer: (X500)");
            dumpX500Key(prefix + "  ", issuer);
        }

        boolean[] keyUsage = x509.getKeyUsage();
        if (keyUsage != null) {
            out.print(prefix + "key-usage: ");
            dumpBits(prefix + "  ", keyUsage);
            out.println();
        }

        Set<String> nonCritSet = x509.getNonCriticalExtensionOIDs();
        if (nonCritSet != null && !nonCritSet.isEmpty()) {
            out.println(prefix + "non-critical-extensions: ");
            for (String oid : nonCritSet)
                out.println(prefix + "  " + formatOID(oid));
        }

        Date notBefore = x509.getNotBefore();
        if (notBefore != null)
            out.println(prefix + "not-before: " + notBefore);
        Date notAfter = x509.getNotAfter();
        if (notAfter != null)
            out.println(prefix + "not-after: " + notAfter);

        PublicKey publicKey = x509.getPublicKey();
        if (publicKey != null) {
            out.print(prefix + "public-key: ");
            dumpKey(prefix + "  ", publicKey, detail);
        }

        BigInteger serialNumber = x509.getSerialNumber();
        if (serialNumber != null)
            out.printf(prefix + "serial-number: %x (%d)\n", serialNumber,
                    serialNumber);

        String sigAlgName = x509.getSigAlgName();
        if (sigAlgName != null)
            out.println(prefix + "sig-alg-name: " + sigAlgName);

        String sigAlgOID = x509.getSigAlgOID();
        if (sigAlgOID != null)
            out.println(prefix + "sig-alg-oid: " + formatOID(sigAlgOID));

        byte[] sigAlgParams = x509.getSigAlgParams();
        if (sigAlgParams != null) {
            out.print(prefix + "sig-alg-params: ");
            dumpBytes(prefix + "  ", sigAlgParams, detail);
        }

        byte[] signature = x509.getSignature();
        if (signature != null) {
            out.print(prefix + "signature: ");
            dumpSignature(prefix + "  ", signature, detail);
        }

        try {
            Collection<List<?>> subjAltNames = x509
                    .getSubjectAlternativeNames();
            if (subjAltNames != null && !subjAltNames.isEmpty()) {
                out.print(prefix + "subject-alternative-names: ");
                _dumpAltNames(prefix + "  ", subjAltNames);
            }
        } catch (CertificateParsingException e) {
            dumpError(prefix + "  ", e, detail);
        }

        // x509.getSubjectDN();

        boolean[] subjectUniqueID = x509.getSubjectUniqueID();
        if (subjectUniqueID != null) {
            out.print(prefix + "subject-unique-id: ");
            dumpBits(prefix + "  ", subjectUniqueID);
            out.println();
        }

        X500Principal subject = x509.getSubjectX500Principal();
        if (subject != null) {
            out.println(prefix + "subject: (X500)");
            dumpX500Key(prefix + "  ", subject);
        }

        try {
            byte[] tbsCert = x509.getTBSCertificate();
            if (tbsCert != null) {
                out.print(prefix + "TBS-certificate: ");
                dumpBytes(prefix + "  ", tbsCert, detail);
            }
        } catch (CertificateEncodingException e) {
            dumpError(prefix + "  ", e, detail);
        }

        int version = x509.getVersion();
        out.println(prefix + "version: " + version);
    }

    void _dumpAltNames(String prefix, Collection<List<?>> altNames) {
        for (List<?> list : altNames) {
            int nt = (Integer) list.get(0);
            if (nt >= 0 && nt < nameTypes.length)
                out.print(prefix + "  " + nameTypes[nt] + " = ");
            else
                out.print(prefix + "  (" + nt + ") = ");
            Object name = list.get(1);
            if (name instanceof byte[])
                dumpBytes(prefix + "    ", (byte[]) name, 1); // full dump
            else
                out.println(name);
        }
    }

    void dumpX500Key(String prefix, X500Principal x500) {
        String canonical = x500.getName(X500Principal.CANONICAL);
        String rfc1779 = x500.getName(X500Principal.RFC1779);
        String rfc2253 = x500.getName(X500Principal.RFC2253);
        out.println(prefix + "canonical = " + canonical);
        if (--detail < 0)
            return;

        out.println(prefix + "rfc1779 = " + rfc1779);
        out.println(prefix + "rfc2253 = " + rfc2253);

        byte[] encoded = x500.getEncoded();
        if (encoded != null) {
            out.print(prefix + "encoded: ");
            dumpBytes(prefix + "  ", encoded, detail);
        }
    }

    void dumpStoreEntry(String prefix, KeyStore keyStore, String alias,
            String password) throws KeyStoreException {
        dumpStoreEntry(prefix, keyStore, alias, password, detail);
    }

    void dumpStoreEntry(String prefix, KeyStore keyStore, String alias,
            String password, int detail) throws KeyStoreException {
        out.println(prefix + "* " + alias + ": ");

        Certificate cert = keyStore.getCertificate(alias);
        if (cert == null) {
            out.println(prefix + "    (not found)");
            return;
        }

        if (detail >= 2) {
            try {
                Date creationDate = keyStore.getCreationDate(alias);
                if (creationDate != null)
                    out.println(prefix + "    creation-date: " + creationDate);
            } catch (UnsupportedOperationException e) {
                dumpError(prefix + "    ", e, detail);
            } catch (ProviderException e) {
                dumpError(prefix + "    ", e, detail);
            }

            try {
                ProtectionParameter protParams;
                if (password == null) {
                    CallbackHandler ch = new TextCallbackHandler();
                    // unsupported: KeyStoreSpi.engineGetEntry
                    protParams = new CallbackHandlerProtection(ch);
                } else {
                    protParams = new PasswordProtection(password.toCharArray());
                }
                Entry entry = keyStore.getEntry(alias, protParams);
                if (entry != null) {
                    String entryType = entry.getClass().getName();
                    out.println(prefix + "    entry: " + entryType);
                }
            } catch (UnsupportedOperationException e) {
                dumpError(prefix + "    ", e, detail);
            } catch (UnrecoverableEntryException e) {
                dumpError(prefix + "    ", e, detail);
            } catch (NoSuchAlgorithmException e) {
                dumpError(prefix + "    ", e, detail);
            }
        }

        if (detail >= 1 && password != null) {
            try {
                Key key = keyStore.getKey(alias, password.toCharArray());
                if (key != null) {
                    out.print(prefix + "    key: ");
                    dumpKey(prefix + "      ", key, detail - 1);
                }
            } catch (UnrecoverableKeyException e) {
                dumpError(prefix + "    ", e, detail);
            } catch (NoSuchAlgorithmException e) {
                dumpError(prefix + "    ", e, detail);
            }
        }

        dumpCert(prefix + "  ", cert, detail);

        if (detail >= 2) {
            Certificate[] chain = keyStore.getCertificateChain(alias);
            if (chain != null)
                for (Certificate cc : chain) {
                    out.print(prefix, "> ");
                    dumpCert(prefix + "  ", cc, detail);
                }
        }
    }

    void dumpKey(String prefix, Key key, int detail) {
        out.println(key.getClass().getName());
        if (--detail < 0)
            return;

        String format = key.getFormat();
        if (format != null)
            out.println(prefix + "format: " + format);

        String algorithm = key.getAlgorithm();
        if (algorithm != null)
            out.println(prefix + "algorithm: " + algorithm);

        byte[] encoded = key.getEncoded();
        if (encoded != null) {
            out.print(prefix + "encoded: ");
            dumpBytes(prefix + "  ", encoded, detail);
        }
        if (key instanceof X509Key) {
            X509Key x509 = (X509Key) key;
            AlgorithmId algorithmId = x509.getAlgorithmId();
            if (algorithmId != null)
                out.println(prefix + "algorithm-id: " + algorithmId);
            try {
                byte[] encodedInternal = x509.getEncodedInternal();
                if (encodedInternal != null) {
                    out.print(prefix + "encoded-internal: ");
                    if (Bytes.equals(encoded, encodedInternal))
                        out.println("(same)");
                    else
                        dumpBytes(prefix + "  ", encodedInternal, detail);
                }
            } catch (InvalidKeyException e) {
                dumpError(prefix + "  ", e, detail);
            }
        }
    }

    public void dumpCipher(Cipher cipher, int detail) {
        int blockSize = cipher.getBlockSize();
        out.println("    block-size = ", blockSize);

        byte[] iv = cipher.getIV();
        if (iv != null)
            dumpBytes("    IV = ", iv, detail);

        AlgorithmParameters params = cipher.getParameters();
        if (params != null)
            out.println("    parameters = ", params);

        ExemptionMechanism mech = cipher.getExemptionMechanism();
        if (mech != null)
            out.println("    exemption = ", mech.getName());
    }

    void _dumpSignature(String prefix, byte[] signature) {
        dumpSignature(prefix, signature, detail);
    }

    void dumpSignature(String prefix, byte[] signature, int detail) {
        assert signature != null;
        // out.print(prefix + "raw-bytes: ");
        dumpBytes(prefix + "    ", signature, detail);

        byte[] md5 = Cryptos.md5(signature);
        out.print(prefix + "md5: ");
        dumpBytes(prefix + "  ", md5, detail + 1); // full dump

        byte[] sha1 = Cryptos.sha1(signature);
        out.print(prefix + "sha1: ");
        dumpBytes(prefix + "  ", sha1, detail + 1); // full dump
    }

    void dumpError(String prefix, Throwable t, int detail) {
        String errType = t.getClass().getSimpleName();
        if (errType.endsWith("Exception"))
            errType = errType.substring(0, errType.length() - 9);
        String mesg = Strings.hyphenatize(errType);
        dumpError(prefix, mesg, t, detail);
    }

    void dumpError(String prefix, String mesg, Throwable t, int detail) {
        assert t != null;
        out.print(prefix);
        out.print("Error");
        if (mesg != null) {
            out.print(" (");
            out.print(mesg);
            out.print(")");
        }
        out.println(": " + t.getMessage());
        if (detail >= 2)
            t.printStackTrace(new PrintWriter(out.toWriter()));
    }

    static final int bitsWidth = 60;
    static final int dumpWidth = 16;

    void dumpBits(String prefix, boolean[] bits) {
        String sep = "\n" + prefix;
        if (bits.length > bitsWidth)
            out.print(sep);
        for (int i = 0; i < bits.length; i++) {
            if (bits[i])
                out.print("1");
            else
                out.print("0");
            if (i % bitsWidth == (bitsWidth - 1))
                out.print(sep);
        }
    }

    void _dumpBytes(String prefix, byte[] bytes) {
        dumpBytes(prefix, bytes, detail);
    }

    void dumpBytes(String prefix, byte[] bytes, int detail) {
        if (bytes == null) {
            out.println("null");
            return;
        }
        out.print(" (" + bytes.length + " bytes) ");
        if (detail < 1) {
            int part = Math.min(dumpWidth, bytes.length);
            String hex = Encodings.HEX.encode(bytes, 0, part);
            if (part < bytes.length)
                hex += " ...";
            out.println(hex);
        } else {
            String sep = "\n" + prefix;
            HexEncoding hexEnc = new HexEncoding(" ", 16, sep);
            if (bytes.length > dumpWidth)
                out.print(sep);
            String hex = hexEnc.encode(bytes).trim();
            out.print(hex);
            out.println();
        }
    }

    private static TextMap<String> oids;
    private static String[]        nameTypes;
    static {
        oids = new TreeTextMap<String>();
        oids.put("2.5.29.14", "SubjectKeyIdentifier");
        oids.put("2.5.29.15", "KeyUsage");
        oids.put("2.5.29.16", "PrivateKeyUsage");
        oids.put("2.5.29.17", "SubjectAlternativeName");
        oids.put("2.5.29.18", "IssuerAlternativeName");
        oids.put("2.5.29.19", "BasicConstraints");
        oids.put("2.5.29.30", "NameConstraints");
        oids.put("2.5.29.33", "PolicyMappings");
        oids.put("2.5.29.35", "AuthorityKeyIdentifier");
        oids.put("2.5.29.36", "PolicyConstraints");
        oids.put("1.2.840.10040.4.3", "SHA-1");
        // oids.put("1.2.840.113549.1.1.5", "??");
        nameTypes = new String[] {
        //
            "otherName", // [0] OtherName
            "rfc822-name", // [1] IA5String
            "DNS-name", // [2] IA5String
            "x400-address", // [3] ORAddress
            "directory-name", // [4] Name
            "edi-party-name", // [5] EDIPartyName
            "URI", // [6] IA5String
            "ip-address", // [7] OCTET STRING
            "registered-id", // [8] OBJECT IDENTIFIER
        };
    }

    private static String formatOID(String oid) {
        String disp = oids.get(oid);
        if (disp == null)
            disp = oid;
        else
            disp = disp + " (" + oid + ")";
        return disp;
    }

}
