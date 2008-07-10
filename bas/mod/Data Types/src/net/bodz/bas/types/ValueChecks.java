package net.bodz.bas.types;

import java.io.File;
import java.util.regex.Pattern;

import net.bodz.bas.io.Files;

public class ValueChecks {

    public static class Regex implements ValueCheck {

        private final Pattern pattern;

        public Regex(String regex) {
            this.pattern = Pattern.compile(regex);
        }

        @Override
        public void check(Object val) throws ValueCheckFailure {
            String s = (val instanceof String) ? (String) val : String
                    .valueOf(val);
            if (!pattern.matcher(s).matches())
                throw new ValueCheckFailure("string doesn't match regex "
                        + pattern.pattern() + ": \n" + s);
        }

    }

    public static class FileAccess implements ValueCheck {

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
        public void check(Object val) throws ValueCheckFailure {
            File f = (File) val;
            if (set(READ) && !f.canRead())
                throw new ValueCheckFailure("can't read " + f);
            if (set(WRITE) && !f.canWrite())
                throw new ValueCheckFailure("can't write " + f);
            if (set(EXECUTE) && !f.canExecute())
                throw new ValueCheckFailure("can't execute " + f);
            if (set(DIRECTORY) && !f.isDirectory())
                throw new ValueCheckFailure("not a directory " + f);
            if (set(FILE) && !f.isFile())
                throw new ValueCheckFailure("not a file " + f);
            if (set(TEXT) && !Files.isText(f))
                throw new ValueCheckFailure("not a text file " + f);
        }
    }

}
