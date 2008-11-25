package net.bodz.bas.sec.pki.util;

import static net.bodz.bas.types.util.ArrayOps.Bytes;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStore.CallbackHandlerProtection;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.ProtectionParameter;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.x500.X500Principal;

import com.sun.security.auth.callback.TextCallbackHandler;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.sec.crypto.Cryptos;
import net.bodz.bas.text.encodings.Encodings;
import net.bodz.bas.text.encodings.HexEncoding;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Name;
import sun.security.x509.X509Key;

public class CertDumper {

    public static final int SIMPLE = 0;
    public static final int DETAIL = 1;
    public static final int FULL   = 2;

    private CharOut         out;
    private int             detail;

    public CertDumper(CharOut out, int detail) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
        this.detail = detail;
    }

    void dump(String prefix, KeyStore keyStore, String alias, String password)
            throws KeyStoreException {
        out.println(prefix + "* " + alias + ": ");

        Certificate cert = keyStore.getCertificate(alias);
        if (cert == null) {
            out.println(prefix + "    (not found)");
            return;
        }

        if (detail >= DETAIL) {
            Date creationDate = keyStore.getCreationDate(alias);
            if (creationDate != null)
                out.println(prefix + "    creation-date: " + creationDate);

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
                if (entry != null)
                    out.println(prefix + "    entry: "
                            + entry.getClass().getName());
            } catch (UnsupportedOperationException e) {
                out.println(prefix + "    Error getEntry: " + e.getMessage());
            } catch (UnrecoverableEntryException e) {
                out.println(prefix + "    Error getEntry: " + e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                out.println(prefix + "    Error getEntry: " + e.getMessage());
            }

            if (password != null) {
                try {
                    Key key = keyStore.getKey(alias, password.toCharArray());
                    if (key != null) {
                        out.print(prefix + "    key: ");
                        dumpKey(prefix + "      ", key);
                    }
                } catch (UnrecoverableKeyException e) {
                    out.println(prefix + "    Error getKey: " + e.getMessage());
                } catch (NoSuchAlgorithmException e) {
                    out.println(prefix + "    Error getKey: " + e.getMessage());
                }
            }
        }

        dump(prefix + "  ", cert);
    }

    void dump(String prefix, Certificate cert) {
        String certType = cert.getType();
        out.print(prefix + certType + ": ");
        if (cert instanceof X509Certificate)
            dumpX509(prefix + "  ", (X509Certificate) cert);
        else {
            out.println(prefix + "(value of " + cert.getClass() + ")");
            out.println(prefix + "  " + cert);
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

    private static String disp(String oid) {
        String disp = oids.get(oid);
        if (disp == null)
            disp = oid;
        else
            disp = disp + " (" + oid + ")";
        return disp;
    }

    public void dumpX509(String prefix, X509Certificate x509) {
        Principal subjectDN = x509.getSubjectDN();
        if (subjectDN instanceof X500Name) {
            out.println(subjectDN);
        } else {
            out.println(subjectDN);
        }
        if (detail < DETAIL) {
            return;
        }

        int basicCons = x509.getBasicConstraints();
        out.printf(prefix + "basic-constraints: %d (%h)\n", basicCons,
                basicCons);

        Set<String> critSet = x509.getCriticalExtensionOIDs();
        if (critSet != null && !critSet.isEmpty()) {
            out.println(prefix + "critical-extensions: ");
            for (String oid : critSet)
                out.println(prefix + "  " + disp(oid));
        }

        try {
            byte[] encoded = x509.getEncoded();
            out.print(prefix + "encoded: ");
            dumpBytes(prefix + "  ", encoded);
        } catch (CertificateEncodingException e) {
            out.println(prefix + "Error encoding: " + e);
        }

        try {
            List<String> extendedKeyUsage = x509.getExtendedKeyUsage();
            if (extendedKeyUsage != null && !extendedKeyUsage.isEmpty()) {
                out.println(prefix + "extended-key-usage: ");
                for (String oid : extendedKeyUsage) {
                    byte[] val = x509.getExtensionValue(oid);
                    out.print(prefix + "  " + disp(oid) + " = ");
                    dumpBytes(prefix + "    ", val);
                }
            }
        } catch (CertificateParsingException e) {
            out.println(prefix + "Error parsing: " + e.getMessage());
        }

        try {
            Collection<List<?>> issAltNames = x509.getIssuerAlternativeNames();
            if (issAltNames != null && !issAltNames.isEmpty()) {
                out.println(prefix + "issuer-alternative-names: ");
                dumpAltNames(prefix + "  ", issAltNames);
            }
        } catch (CertificateParsingException e) {
            out.println(prefix + "Error parsing: " + e.getMessage());
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
            dumpX500(prefix + "  ", issuer);
        }

        boolean[] keyUsage = x509.getKeyUsage();
        if (keyUsage != null) {
            out.print(prefix + "key-usage: ");
            dumpBits(prefix + "  ", keyUsage);
        }

        Set<String> nonCritSet = x509.getNonCriticalExtensionOIDs();
        if (nonCritSet != null && !nonCritSet.isEmpty()) {
            out.println(prefix + "non-critical-extensions: ");
            for (String oid : nonCritSet)
                out.println(prefix + "  " + disp(oid));
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
            dumpKey(prefix + "  ", publicKey);
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
            out.println(prefix + "sig-alg-oid: " + disp(sigAlgOID));

        byte[] sigAlgParams = x509.getSigAlgParams();
        if (sigAlgParams != null) {
            out.print(prefix + "sig-alg-params: ");
            dumpBytes(prefix + "  ", sigAlgParams);
        }

        byte[] signature = x509.getSignature();
        if (signature != null) {
            out.print(prefix + "signature: ");
            dumpSignature(prefix + "  ", signature);
        }

        try {
            Collection<List<?>> subjAltNames = x509
                    .getSubjectAlternativeNames();
            if (subjAltNames != null && !subjAltNames.isEmpty()) {
                out.print(prefix + "subject-alternative-names: ");
                dumpAltNames(prefix + "  ", subjAltNames);
            }
        } catch (CertificateParsingException e) {
            out.println(prefix + "Error parsing: " + e.getMessage());
        }

        // x509.getSubjectDN();

        boolean[] subjectUniqueID = x509.getSubjectUniqueID();
        if (subjectUniqueID != null) {
            out.print(prefix + "subject-unique-id: ");
            dumpBits(prefix + "  ", subjectUniqueID);
        }

        X500Principal subject = x509.getSubjectX500Principal();
        if (subject != null) {
            out.println(prefix + "subject: (X500)");
            dumpX500(prefix + "  ", subject);
        }

        try {
            byte[] tbsCert = x509.getTBSCertificate();
            if (tbsCert != null) {
                out.print(prefix + "TBS-certificate: ");
                dumpBytes(prefix + "  ", tbsCert);
            }
        } catch (CertificateEncodingException e) {
            out.println(prefix + "Error encoding: " + e.getMessage());
        }

        int version = x509.getVersion();
        out.println(prefix + "version: " + version);
    }

    void dumpAltNames(String prefix, Collection<List<?>> altNames) {
        for (List<?> list : altNames) {
            int nt = (Integer) list.get(0);
            if (nt >= 0 && nt < nameTypes.length)
                out.print(prefix + "  " + nameTypes[nt] + " = ");
            else
                out.print(prefix + "  (" + nt + ") = ");
            Object name = list.get(1);
            if (name instanceof byte[])
                dumpBytes(prefix + "    ", (byte[]) name);
            else
                out.println(name);
        }
    }

    void dumpX500(String prefix, X500Principal x500) {
        String canonical = x500.getName(X500Principal.CANONICAL);
        String rfc1779 = x500.getName(X500Principal.RFC1779);
        String rfc2253 = x500.getName(X500Principal.RFC2253);
        out.println(prefix + "canonical = " + canonical);
        out.println(prefix + "rfc1779 = " + rfc1779);
        out.println(prefix + "rfc2253 = " + rfc2253);
        byte[] encoded = x500.getEncoded();
        if (encoded != null) {
            out.print(prefix + "encoded: ");
            dumpBytes(prefix + "  ", encoded);
        }
    }

    void dumpKey(String prefix, Key key) {
        out.println(key.getClass().getName());
        String format = key.getFormat();
        if (format != null)
            out.println(prefix + "format: " + format);

        String algorithm = key.getAlgorithm();
        if (algorithm != null)
            out.println(prefix + "algorithm: " + algorithm);

        byte[] encoded = key.getEncoded();
        if (encoded != null) {
            out.print(prefix + "encoded: ");
            dumpBytes(prefix + "  ", encoded);
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
                        dumpBytes(prefix + "  ", encodedInternal);
                }
            } catch (InvalidKeyException e) {
                out.println(prefix + "Error get internal: " + e.getMessage());
            }
        }
    }

    void dumpSignature(String prefix, byte[] signature) {
        assert signature != null;
        // out.print(prefix + "raw-bytes: ");
        dumpBytes(prefix + "    ", signature);

        byte[] md5 = Cryptos.md5(signature);
        out.print(prefix + "md5: ");
        dumpBytes(prefix + "  ", md5, FULL);

        byte[] sha1 = Cryptos.sha1(signature);
        out.print(prefix + "sha1: ");
        dumpBytes(prefix + "  ", sha1, FULL);
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

    void dumpBytes(String prefix, byte[] bytes) {
        dumpBytes(prefix, bytes, detail);
    }

    void dumpBytes(String prefix, byte[] bytes, int detail) {
        if (bytes == null) {
            out.println("null");
            return;
        }
        out.print(" (" + bytes.length + " bytes) ");
        if (detail < FULL) {
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

}
