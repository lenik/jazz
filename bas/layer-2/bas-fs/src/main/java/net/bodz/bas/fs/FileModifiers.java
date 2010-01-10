package net.bodz.bas.fs;

import java.io.File;

public class FileModifiers {

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

    public boolean isReadable(int bits) {
        return (bits & READ) != 0;
    }

    public boolean isWritable(int bits) {
        return (bits & WRITE) != 0;
    }

    public boolean isExecutable(int bits) {
        return (bits & EXECUTE) != 0;
    }

    public boolean isVisible(int bits) {
        return (bits & HIDDEN) == 0;
    }

    public boolean isArchive(int bits) {
        return (bits & ARCHIVE) != 0;
    }

    public boolean isSystem(int bits) {
        return (bits & SYSTEM) != 0;
    }

    public boolean isEmpty(int bits) {
        return (bits & ZERO) != 0;
    }

    public boolean isNonEmpty(int bits) {
        return (bits & NZERO) != 0;
    }

    public boolean isExisted(int bits) {
        return (bits & EXIST) != 0;
    }

    public boolean isNonExisted(int bits) {
        return (bits & NEXIST) != 0;
    }

    public boolean isFile(int bits) {
        return (bits & FILE) != 0;
    }

    public boolean isDirectory(int bits) {
        return (bits & DIRECTORY) != 0;
    }

    public boolean isText(int bits) {
        return (bits & TEXT) != 0;
    }

    public boolean isBinary(int bits) {
        return (bits & BINARY) != 0;
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
                throw new IllegalArgumentException(String.format("illegal mask char: \'%c\' (%d)", c, c));
            }
        }
        return bits;
    }

    private static char[] bitchars = "rwx-HAS-zseEfd--TB--------------".toCharArray();

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

    public static int getFileBits(File file) {
        int allBits = FileMask.ALL.check(file);
        return allBits;
    }

    public static String format(File file) {
        return format(getFileBits(file));
    }

}
