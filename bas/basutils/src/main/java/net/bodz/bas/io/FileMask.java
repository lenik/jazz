package net.bodz.bas.io;

import java.io.File;

import net.bodz.bas.nls.SysNLS;

public class FileMask {

    public static final int READ = 0x00000001;
    public static final int WRITE = 0x00000002;
    public static final int EXECUTE = 0x00000004;
    public static final int ACCESS = READ | WRITE | EXECUTE;

    public static final int HIDDEN = 0x00000010;
    public static final int ARCHIVE = 0x00000020;
    public static final int SYSTEM = 0x00000040;
    public static final int ATTRIB = ARCHIVE | HIDDEN | SYSTEM;

    public static final int ZERO = 0x00000100;
    public static final int NZERO = 0x00000200;
    public static final int EXIST = 0x00000400;
    public static final int NEXIST = 0x00000800;
    public static final int FILEINFO = ZERO | NZERO | EXIST | NEXIST;

    public static final int FILE = 0x00001000;
    public static final int DIRECTORY = 0x00002000;
    public static final int OBJTYPE = FILE | DIRECTORY;

    public static final int TEXT = 0x00010000;
    public static final int BINARY = 0x00020000;
    public static final int CNTTYPE = TEXT | BINARY;

    private static char[] bitchars = "rwx-HAS-zseEfd--TB--------------" //$NON-NLS-1$
    .toCharArray();

    private final int mask;
    private final int bits;

    public FileMask(int bits, int mask) {
        if ((bits & CNTTYPE) != 0) {
            bits |= FILE | EXIST;
            mask |= FILE | EXIST;
        }
        this.mask = mask;
        this.bits = bits & mask;
    }

    public FileMask(int bits) {
        this(bits, bits);
    }

    /**
     * bits/mask
     */
    public FileMask(String str) {
        int slash = str.indexOf('/');
        int bits, mask;
        if (slash != -1) {
            bits = parse(str.substring(0, slash));
            mask = parse(str.substring(slash + 1));
        } else {
            bits = mask = parse(str);
        }
        if ((bits & CNTTYPE) != 0) {
            bits |= FILE | EXIST;
            mask |= FILE | EXIST;
        }
        this.bits = bits;
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }

    public int getBits() {
        return bits;
    }

    private final boolean masked(int bits) {
        return (mask & bits) != 0;
    }

    public int check(File file) {
        int bits = 0;

        if (masked(ACCESS)) {
            if (file.canRead())
                bits |= READ;
            if (file.canWrite())
                bits |= WRITE;
            if (file.canExecute())
                bits |= EXECUTE;
        }

        if (masked(ATTRIB)) {
            if (masked(HIDDEN))
                if (file.isHidden())
                    bits |= HIDDEN;
                else {
                    if (file.getName().startsWith(".")) //$NON-NLS-1$
                        bits |= HIDDEN;
                }
            // if (check(ARCHIVE | SYSTEM)) ; // not supported
        }

        if (masked(FILEINFO)) {
            if (file.exists()) {
                bits |= EXIST;
                if (file.length() == 0l)
                    bits |= ZERO;
                else
                    bits |= NZERO;
            } else
                bits |= NEXIST;
        }

        if (masked(OBJTYPE))
            if (file.isDirectory())
                bits |= DIRECTORY;
            else
                bits |= FILE;

        if (masked(CNTTYPE) && (bits & EXIST) != 0)
            if (Files.isText(file))
                bits |= TEXT;
            else
                bits |= BINARY;

        return bits;
    }

    public boolean set() {
        return mask != 0;
    }

    public boolean test(File file) {
        int bits = check(file);
        return (bits & mask) == this.bits;
    }

    @Override
    public String toString() {
        return format(bits) + "/" + format(mask); //$NON-NLS-1$
    }

    public static int parse(String str) {
        int bits = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            int c = str.charAt(i);
            switch (c) {
            case 'r':
                bits |= READ;
                break;
            case 'w':
                bits |= WRITE;
                break;
            case 'x':
                bits |= EXECUTE;
                break;
            case 'H':
                bits |= HIDDEN;
                break;
            case 'A':
                bits |= ARCHIVE;
                break;
            case 'S':
                bits |= SYSTEM;
                break;
            case 'z':
                bits |= ZERO;
                break;
            case 's':
                bits |= NZERO;
                break;
            case 'e':
                bits |= EXIST;
                break;
            case 'E':
                bits |= NEXIST;
                break;
            case 'f':
                bits |= FILE;
                break;
            case 'd':
                bits |= DIRECTORY;
                break;
            case 'T':
                bits |= TEXT;
                break;
            case 'B':
                bits |= BINARY;
                break;
            default:
                throw new IllegalArgumentException(String.format(SysNLS.getString("FileMask.errMask_cd"), c, c)); //$NON-NLS-1$
            }
        }
        return bits;
    }

    public static String format(int bits) {
        StringBuffer buf = new StringBuffer();
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((bits & mask) != 0)
                buf.append(bitchars[i]);
            mask <<= 1;
        }
        return buf.toString();
    }

    public static String format(String str) {
        return format(parse(str));
    }

    public static final FileMask ALL = new FileMask(-1);
    public static final FileMask BASIC = new FileMask(ACCESS | FILEINFO | OBJTYPE);

    public static int getFileBits(File file) {
        int allBits = ALL.check(file);
        return allBits;
    }

    public static String format(File file) {
        return format(getFileBits(file));
    }

}
