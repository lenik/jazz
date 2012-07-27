package net.bodz.bas.vfs;

/**
 * This class defines bit constants for file modifiers, and static helper functions to encode/decode
 * with the modifier string.
 * <p>
 * There serveral kind of modifiers:
 * <ul>
 * <li>Access permissions, like rwx
 * <li>System file type (used by Win32) like archiva, system, hidden.
 * <li>File existence information, exist/non-exist/empty/non-empty
 * <li>File node type, like file or directory
 * <li>File content type: text/binary
 * </ul>
 * 
 * And more modifier may be added in future.
 */
public class FileModifier {

    public static final int READABLE = 0x00000001;
    public static final int WRITABLE = 0x00000002;
    public static final int EXECUTABLE = 0x00000004;
    public static final int SEEKABLE = 0x00000008;
    public static final int MASK_ACCESSIBLE = READABLE | WRITABLE | EXECUTABLE | SEEKABLE;

    public static final int HIDDEN = 0x00000010;
    public static final int ARCHIVE = 0x00000020;
    public static final int SYSTEM = 0x00000040;
    public static final int MASK_ATTRIB = ARCHIVE | HIDDEN | SYSTEM;

    public static final int EXIST = 0x00000100;
    public static final int NEXIST = 0x00000200;
    public static final int MASK_EXISTENCE = EXIST | NEXIST;

    public static final int EMPTY = 0x00000400;
    public static final int NEMPTY = 0x00000800;
    public static final int MASK_LENGTHY = EMPTY | NEMPTY;

    public static final int FILE = 0x00001000;
    public static final int DIRECTORY = 0x00002000;
    public static final int MASK_OBJTYPE = FILE | DIRECTORY;

    public static final int TEXT = 0x00010000;
    public static final int BINARY = 0x00020000;
    public static final int CNTTYPE = TEXT | BINARY;

    public static final int MASK_ALL = -1;
    public static final int MASK_BASIC = MASK_ACCESSIBLE | MASK_EXISTENCE | MASK_OBJTYPE;

    public static boolean isReadable(int bits) {
        return (bits & READABLE) != 0;
    }

    public static boolean isWritable(int bits) {
        return (bits & WRITABLE) != 0;
    }

    public static boolean isExecutable(int bits) {
        return (bits & EXECUTABLE) != 0;
    }

    public static boolean isVisible(int bits) {
        return (bits & HIDDEN) == 0;
    }

    public static boolean isArchive(int bits) {
        return (bits & ARCHIVE) != 0;
    }

    public static boolean isSystem(int bits) {
        return (bits & SYSTEM) != 0;
    }

    public static boolean isEmpty(int bits) {
        return (bits & EMPTY) != 0;
    }

    public static boolean isNonEmpty(int bits) {
        return (bits & NEMPTY) != 0;
    }

    public static boolean isExisted(int bits) {
        return (bits & EXIST) != 0;
    }

    public static boolean isNonExisted(int bits) {
        return (bits & NEXIST) != 0;
    }

    public static boolean isFile(int bits) {
        return (bits & FILE) != 0;
    }

    public static boolean isDirectory(int bits) {
        return (bits & DIRECTORY) != 0;
    }

    public static boolean isText(int bits) {
        return (bits & TEXT) != 0;
    }

    public static boolean isBinary(int bits) {
        return (bits & BINARY) != 0;
    }

    public static int parse(String str) {
        int bits = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            int c = str.charAt(i);
            switch (c) {
            case '-':
                continue;
            case 'r':
                bits |= READABLE;
                break;
            case 'w':
                bits |= WRITABLE;
                break;
            case 'x':
                bits |= EXECUTABLE;
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
                bits |= EMPTY;
                break;
            case 's':
                bits |= NEMPTY;
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
                throw new IllegalArgumentException(String.format("illegal mask char: \'%c\' (%d)", c, c));
            }
        }
        return bits;
    }

    private static char[] bitchars = "rwx-HAS-zseEfd--TB--------------".toCharArray();

    /**
     * Convert the modifier bits to a readable string.
     * 
     * A full-length modifier string display '-' at the corresponding position if that bit isn't set
     * , but that's too verbose and hard to read. So only the defined bits are converted and
     * included in the result string.
     * 
     * @param bits
     *            The modifier bits to be formatted.
     * @return Non-empty modifier string. If there is no bits, a single <code>'-'</code> is
     *         returned.
     */
    public static String format(int bits) {
        if (bits == 0)
            return "-";

        StringBuilder buf = new StringBuilder();
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((bits & mask) != 0)
                buf.append(bitchars[i]);
            mask <<= 1;
        }
        return buf.toString();
    }

    /**
     * Normalize a modifier string.
     * <p>
     * This function parse and re-format a given modifier string, to make a comparable normalized
     * string.
     * 
     * @param Non
     *            -<code>null</code> modifier string.
     * @return Non-<code>null</code> normalized string.
     */
    public static String normalize(String str) {
        return format(parse(str));
    }

    /**
     * In common cases, some modifier bits needs to reuse some existing tests.
     * <p>
     * This function will add missing dependencies which are required by most of file systesm.
     * 
     * @param mask
     *            The mask modifier bits to be checked.
     * @return Fixed mask bits contains all the necessary dependency modifier bits.
     */
    public static int addDependencies(int mask) {
        if ((mask & CNTTYPE) != 0)
            mask |= FILE | EXIST;
        return mask;
    }

}
