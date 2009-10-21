package net.bodz.bas.las.units;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang2.Filt1;
import net.bodz.bas.las.LasUnit;
import net.bodz.bas.las._LasUnit;
import net.bodz.bas.types.util.Dates;

public class PrettyLogger extends _LasUnit {

    private final CharOut out;

    private static class ToString extends Filt1<String, Object> {

        @Override
        public String filter(Object a) {
            return String.valueOf(a);
        }

    }

    public static final ToString toString    = new ToString();

    public DateFormat            dateFormat  = Dates.dateTimeFormat;
    public String                prefix;
    public String                tabChar     = "\t";                //$NON-NLS-1$
    public Filt1<String, Object> valueFormat = toString;
    public boolean               printClassName;
    public boolean               dumpFullStack;

    private int                  level;

    public PrettyLogger(LasUnit prev, CharOut out) {
        super(prev);
        this.out = out;
    }

    void printHeader() {
        if (dateFormat != null) {
            String date = dateFormat.format(new Date());
            out.print(date);
        }
        if (prefix != null)
            out.print(prefix);
        if (tabChar != null)
            for (int i = 0; i < level; i++)
                out.print(tabChar);
        out.print(" "); //$NON-NLS-1$
    }

    @Override
    protected boolean _enter(Object... args) {
        printHeader();
        StackTraceElement stack = Caller.getStack(2);
        if (printClassName) {
            out.print(stack.getClassName());
            out.print("."); //$NON-NLS-1$
        }
        out.print(stack.getMethodName());
        if (valueFormat != null) {
            out.print("("); //$NON-NLS-1$
            for (int i = 0; i < args.length; i++) {
                String arg = valueFormat.eval(args[i]);
                if (i != 0)
                    out.print(", "); //$NON-NLS-1$
                out.print(arg);
            }
            out.print(")"); //$NON-NLS-1$
        }
        out.println();
        out.flush();
        level++;
        return true;
    }

    @Override
    protected void _leave() {
        level--;
    }

    @Override
    protected <T> T _leave(T returnValue) throws IllegalUsageError {
        level--;
        printHeader();
        out.print("return"); //$NON-NLS-1$
        if (valueFormat != null) {
            out.print("("); //$NON-NLS-1$
            String s = valueFormat.filter(returnValue);
            out.print(s);
            out.print(")"); //$NON-NLS-1$
        }
        out.println();
        out.flush();
        return returnValue;
    }

    @Override
    protected <T extends Throwable> T _leave(T t) throws T {
        assert t != null;
        level--;
        printHeader();
        out.print("throws "); //$NON-NLS-1$
        String extype = t.getClass().getName();
        out.print(extype);
        out.print(": "); //$NON-NLS-1$
        out.print(t.getMessage());
        if (dumpFullStack) {
            StringWriter buf = new StringWriter();
            t.printStackTrace(new PrintWriter(buf));
            String s = buf.toString();
            out.println();
            out.print(s);
        }
        out.println();
        out.flush();
        throw t;
    }

}
