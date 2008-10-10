package net.bodz.bas.types;

import java.io.File;
import java.util.regex.Pattern;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CheckFailure;

public class Checks {

    public static void setOnlyOne(Object... args) throws CheckException {
        int last = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (last != -1)
                    throw new CheckException("only one is allowed: " + last
                            + ", " + i);
                last = i;
            }
        }
        if (last == -1)
            throw new CheckException("no argument specified");
    }

    public static class Regex implements Checker {

        private final Pattern pattern;

        public Regex(String regex) {
            this.pattern = Pattern.compile(regex);
        }

        @Override
        public void check(Object val) throws CheckFailure {
            String s = (val instanceof String) ? (String) val : String
                    .valueOf(val);
            if (!pattern.matcher(s).matches())
                throw new CheckFailure("string doesn't match regex "
                        + pattern.pattern() + ": \n" + s);
        }

    }

    public static class FileAccess implements Checker {

        public static final int READ      = 1;
        public static final int WRITE     = 2;
        public static final int EXECUTE   = 4;
        public static final int DIRECTORY = 8;
        public static final int FILE      = 16;
        public static final int TEXT      = 32;

        private int             mode;

        public FileAccess(int mode) {
            this.mode = mode;
        }

        public FileAccess(String mode) {
            int m = 0;
            for (char c : mode.toLowerCase().toCharArray())
                switch (c) {
                case 'r':
                    m |= READ;
                    break;
                case 'w':
                    m |= WRITE;
                    break;
                case 'x':
                    m |= EXECUTE;
                    break;
                case 'd':
                    m |= DIRECTORY;
                    break;
                case 'f':
                    m |= FILE;
                    break;
                case 'T':
                    m |= TEXT;
                    break;
                case '+':
                    m |= READ | WRITE;
                    break;
                default:
                    throw new IllegalArgumentException("invalid mode char: "
                            + c);
                }
            this.mode = m;
        }

        private boolean set(int mask) {
            return (mode & mask) != 0;
        }

        @Override
        public void check(Object val) throws CheckFailure {
            File f = (File) val;
            if (set(READ) && !f.canRead())
                throw new CheckFailure("can't read " + f);
            if (set(WRITE) && !f.canWrite())
                throw new CheckFailure("can't write " + f);
            if (set(EXECUTE) && !f.canExecute())
                throw new CheckFailure("can't execute " + f);
            if (set(DIRECTORY) && !f.isDirectory())
                throw new CheckFailure("not a directory " + f);
            if (set(FILE) && !f.isFile())
                throw new CheckFailure("not a file " + f);
            if (set(TEXT) && !Files.isText(f))
                throw new CheckFailure("not a text file " + f);
        }
    }

}
