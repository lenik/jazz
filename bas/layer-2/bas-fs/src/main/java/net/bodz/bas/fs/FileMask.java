package net.bodz.bas.fs;

import static net.bodz.bas.fs.FileModifiers.ACCESS;
import static net.bodz.bas.fs.FileModifiers.ARCHIVE;
import static net.bodz.bas.fs.FileModifiers.ATTRIB;
import static net.bodz.bas.fs.FileModifiers.BINARY;
import static net.bodz.bas.fs.FileModifiers.CNTTYPE;
import static net.bodz.bas.fs.FileModifiers.DIRECTORY;
import static net.bodz.bas.fs.FileModifiers.EXECUTE;
import static net.bodz.bas.fs.FileModifiers.EXIST;
import static net.bodz.bas.fs.FileModifiers.FILE;
import static net.bodz.bas.fs.FileModifiers.FILEINFO;
import static net.bodz.bas.fs.FileModifiers.HIDDEN;
import static net.bodz.bas.fs.FileModifiers.NEXIST;
import static net.bodz.bas.fs.FileModifiers.NZERO;
import static net.bodz.bas.fs.FileModifiers.OBJTYPE;
import static net.bodz.bas.fs.FileModifiers.READ;
import static net.bodz.bas.fs.FileModifiers.SYSTEM;
import static net.bodz.bas.fs.FileModifiers.TEXT;
import static net.bodz.bas.fs.FileModifiers.WRITE;
import static net.bodz.bas.fs.FileModifiers.ZERO;
import static net.bodz.bas.fs.FileModifiers.format;

import java.io.File;
import java.io.IOException;

public class FileMask {

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

            // dot-files are hidden by default in unix system.
            // else {
            // if (file.getName().startsWith("."))
            // bits |= HIDDEN;
            // }

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

        if (masked(CNTTYPE) && (bits & EXIST) != 0) {
            try {
                byte[] block;
                block = new PlainFile(file).forRead().readBytes(TextOrBinary.textLookSize);
                if (TextOrBinary.isText(block))
                    bits |= TEXT;
                else
                    bits |= BINARY;
            } catch (IOException e) {
            }
        }
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
        return format(bits) + "/" + format(mask);
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

    public static final FileMask ALL = new FileMask(-1);
    public static final FileMask BASIC = new FileMask(ACCESS | FILEINFO | OBJTYPE);

}
