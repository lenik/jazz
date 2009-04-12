package net.bodz.bas.types;

import java.io.File;
import java.lang.reflect.AnnotatedElement;
import java.util.regex.Pattern;

import net.bodz.bas.cli.a.CheckBy;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CheckFailure;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.nls.SysNLS;
import net.bodz.bas.nls.TypesNLS;
import net.bodz.bas.types.util.Types;

public class Checks {

    public static Checker getChecker(AnnotatedElement elm) throws CreateException {
        CheckBy checkBy = elm.getAnnotation(CheckBy.class);
        return getChecker(checkBy);
    }

    public static Checker getChecker(CheckBy checkBy) throws CreateException {
        if (checkBy == null)
            return null;
        Class<? extends Checker> checkerClass = checkBy.value();
        if (checkerClass == Checker.class)
            return null;
        String param = checkBy.param();
        if (param.isEmpty())
            param = null;
        if (param == null)
            return Types.getClassInstance(checkerClass);
        else
            return Types.getClassInstance(checkerClass, param);
    }

    public static void setOnlyOne(Object... args) throws CheckException {
        int last = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (last != -1)
                    throw new CheckException(TypesNLS.getString("Checks.0") + last //$NON-NLS-1$
                            + TypesNLS.getString("Checks.1") + i); //$NON-NLS-1$
                last = i;
            }
        }
        if (last == -1)
            throw new CheckException(TypesNLS.getString("Checks.2")); //$NON-NLS-1$
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
                throw new CheckFailure(TypesNLS.getString("Checks.3") //$NON-NLS-1$
                        + pattern.pattern() + TypesNLS.getString("Checks.4") + s); //$NON-NLS-1$
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
                    throw new IllegalArgumentException(TypesNLS.getString("Checks.invalidModeChar") //$NON-NLS-1$
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
                throw new CheckFailure(TypesNLS.getString("Checks.cantRead") + f); //$NON-NLS-1$
            if (set(WRITE) && !f.canWrite())
                throw new CheckFailure(TypesNLS.getString("Checks.cantWrite") + f); //$NON-NLS-1$
            if (set(EXECUTE) && !f.canExecute())
                throw new CheckFailure(TypesNLS.getString("Checks.cantExecute") + f); //$NON-NLS-1$
            if (set(DIRECTORY) && !f.isDirectory())
                throw new CheckFailure(TypesNLS.getString("Checks.notDir") + f); //$NON-NLS-1$
            if (set(FILE) && !f.isFile())
                throw new CheckFailure(TypesNLS.getString("Checks.notFile") + f); //$NON-NLS-1$
            if (set(TEXT) && !Files.isText(f))
                throw new CheckFailure(TypesNLS.getString("Checks.notTextFile") + f); //$NON-NLS-1$
        }
    }

}
