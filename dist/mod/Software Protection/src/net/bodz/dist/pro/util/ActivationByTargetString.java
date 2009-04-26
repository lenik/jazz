package net.bodz.dist.pro.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.prefs.Preferences;

import net.bodz.bas.a.WebSite;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.util.Ns;
import net.bodz.dist.pro.pm.ProtectException;
import net.bodz.dist.pro.pm.ProtectionModel;
import net.bodz.dist.pro.seals.CodeSet;
import net.bodz.dist.pro.seals.Sequence;
import net.bodz.dist.pro.util.ActivationByTargetStringTest;

/**
 * @test {@link ActivationByTargetStringTest}
 */
public class ActivationByTargetString {

    private static final String  KEY_HOSTID        = "hostId";
    private static final String  KEY_ACTIVATE_CODE = "activateCode";

    private static final Charset encoding          = Charset.forName("utf-8");

    private final String         prefix;
    private final byte[]         prefixBytes;
    private final int            segments;
    private final String         website;

    private Preferences          preferences;
    private String               hostId;
    private byte[]               hostBytes;
    private String               activateCode;

    ActivationByTargetString(Class<?> clazz) throws ProtectException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        Activation activation = Ns.getN(clazz, Activation.class);
        if (activation == null)
            throw new IllegalUsageError("No activation info");
        this.prefix = activation.prefix();
        this.prefixBytes = prefix.getBytes(encoding);
        this.segments = activation.segments();
        String[] websites = Ns._getValue(clazz, WebSite.class);
        if (websites != null && websites.length != 0)
            website = websites[0];
        else
            website = null;

        preferences = Preferences.userNodeForPackage(clazz);
        activateCode = preferences.get(KEY_ACTIVATE_CODE, null);
    }

    public ActivationByTargetString(String hostId) throws ProtectException {
        website = null;
        String[] segs = hostId.split("-");
        prefix = segs[0];
        prefixBytes = prefix.getBytes(encoding);
        segments = segs.length - 1;
        hostBytes = new byte[segments * 4];
        ByteBuffer out = ByteBuffer.wrap(hostBytes);
        for (int i = 1; i <= segments; i++) {
            String s = segs[i];
            int n = CodeSet.decode(s);
            out.putInt(n);
        }
        this.hostId = hostId;
        preferences = null;
    }

    public ActivationByTargetString(Class<?> clazz, ProtectionModel pm) throws ProtectException {
        this(clazz);
        if (pm == null)
            throw new NullPointerException("pm");

        hostBytes = new byte[segments * 4];
        ByteBuffer out = ByteBuffer.wrap(hostBytes);
        Sequence seq = pm.getSequence();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < segments; i++) {
            if (i == 0)
                buf.append(prefix);
            buf.append("-");
            int n = seq.next();
            buf.append(CodeSet.encode(n));
            out.putInt(n);
        }
        hostId = buf.toString();
    }

    public String getWebsite() {
        return website;
    }

    public String getHostId() {
        return hostId;
    }

    public boolean isActivated() {
        return activateCode != null;
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode.trim().toUpperCase();
    }

    public void save() {
        if (preferences == null)
            throw new NullPointerException("preferences");
        preferences.put(KEY_HOSTID, hostId);
        preferences.put(KEY_ACTIVATE_CODE, activateCode);
    }

    static final MessageDigest digest;
    static {
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    byte[] getHostMac() {
        byte[] v = digest.digest(hostBytes);
        return v;
    }

    public String getTargetString() throws ParseException {
        if (activateCode == null)
            return null;
        byte[] hostMac = getHostMac();
        int last = activateCode.lastIndexOf('-');
        if (last == -1)
            throw new ParseException("No checksum");
        String code = activateCode.substring(0, last);

        String checksum = digest(code);
        if (!checksum.equalsIgnoreCase(activateCode.substring(last + 1)))
            throw new ParseException("Error Checksum");

        String[] segs = code.split("-");

        byte[] segbuf = new byte[4];
        ByteBuffer segbb = ByteBuffer.wrap(segbuf);
        char[] target = new char[segs.length];
        for (int i = 0; i < segs.length; i++) {
            String s = segs[i];
            int n = CodeSet.decode(s);
            segbb.clear();
            segbb.putInt(n);
            digest.reset();
            digest.update(prefixBytes);
            digest.update(hostMac);
            byte[] v = digest.digest(segbuf);
            // int c = ((v[0] & 0xff) << 8) | v[1];
            int t0 = (v[0] & 0xff);
            int t1 = (v[1] & 0xff) << 8;
            target[i] = (char) (t0 | t1);
        }
        return new String(target);
    }

    public static String digest(String s) {
        return _digest(s, 0x10000000);
    }

    static String _digest(String s, long mod) {
        byte[] bytes = s.getBytes(encoding);
        digest.reset();
        byte[] v = digest.digest(bytes);
        long remain = new BigInteger(v).mod(BigInteger.valueOf(mod)).longValue();
        return CodeSet.encode(remain);
    }

    public String generateFor(String targetString) {
        char[] chars = targetString.toCharArray();
        StringBuffer codebuf = new StringBuffer();
        byte[] segbuf = new byte[4];
        ByteBuffer segbb = ByteBuffer.wrap(segbuf);
        byte[] hostMac = getHostMac();
        long count = 0;
        for (int i = 0; i < chars.length; i++) {
            int target = chars[i];
            byte t0 = (byte) (target & 0xff);
            byte t1 = (byte) (target >> 8);
            // if (t1 != 0)
            // throw new UnsupportedOperationException(//
            // "Currently only ascii chars are supported");
            int k;
            for (k = 0; k < Integer.MAX_VALUE; k++) {
                count++;
                digest.reset();
                digest.update(prefixBytes);
                digest.update(hostMac);
                segbb.clear();
                segbb.putInt(k);
                byte[] v = digest.digest(segbuf);
                if (v[0] == t0)
                    if (v[1] == t1)
                        break;
            }
            if (k == Integer.MAX_VALUE)
                throw new UnsupportedOperationException("Unsupported target string: "
                        + targetString);
            String seg = CodeSet.encode(k);
            if (i != 0)
                codebuf.append('-');
            codebuf.append(seg);
        }
        System.out.println("Search space: " + count);
        System.out.printf("Tries per char: %.3f\n", (double) count / chars.length);
        String code = codebuf.toString();
        return code + "-" + digest(code);
    }

}
