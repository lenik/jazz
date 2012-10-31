package net.bodz.bas.sio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public abstract class AbstractPrintOut
        extends AbstractCharOut
        implements IPrintOut {

    private final static char[] NULL = "(null)".toCharArray();
    private final static char NEWLINE = '\n';

    @Override
    public void print(String s) {
        try {
            // char[] chars = s == null ? NULL : s.toCharArray();
            // write(chars, 0, chars.length);
            if (s == null)
                write(NULL);
            else
                write(s);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public void print(boolean b) {
        print(String.valueOf(b));
    }

    @Override
    public void print(char c) {
        try {
            write(c);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public void print(char[] s) {
        if (s == null)
            throw new NullPointerException("s");
        try {
            write(s, 0, s.length);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public void print(double d) {
        print(String.valueOf(d));
    }

    @Override
    public void print(float f) {
        print(String.valueOf(f));
    }

    @Override
    public void print(int i) {
        print(String.valueOf(i));
    }

    @Override
    public void print(long l) {
        print(String.valueOf(l));
    }

    @Override
    public void print(Object obj) {
        print(String.valueOf(obj));
    }

    @Override
    public void print(Object... args) {
        for (Object arg : args)
            print(arg);
    }

    @Override
    public void println() {
        try {
            write(NEWLINE);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public void println(boolean x) {
        print(x);
        println();
    }

    @Override
    public void println(char x) {
        print(x);
        println();
    }

    @Override
    public void println(char[] x) {
        print(x);
        println();
    }

    @Override
    public void println(double x) {
        print(x);
        println();
    }

    @Override
    public void println(float x) {
        print(x);
        println();
    }

    @Override
    public void println(int x) {
        print(x);
        println();
    }

    @Override
    public void println(long x) {
        print(x);
        println();
    }

    @Override
    public void println(Object x) {
        print(x);
        println();
    }

    @Override
    public void println(String x) {
        print(x);
        println();
    }

    @Override
    public void println(Object... args) {
        if (args.length == 0) {
            println();
            return;
        }
        int max = args.length - 1;
        for (int i = 0; i < max; i++)
            print(args[i]);
        println(args[max]);
    }

    @Override
    public void printf(Locale l, String format, Object... args) {
        print(String.format(l, format, args));
    }

    @Override
    public void printf(String format, Object... args) {
        print(String.format(format, args));
    }

    @Override
    public final void flush(boolean strict) {
        try {
            _flush(strict);
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public final void flush() {
        try {
            _flush();
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public final void close() {
        try {
            _close();
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    protected void _flush(boolean strict)
            throws IOException {
    }

    protected void _flush()
            throws IOException {
        _flush(true);
    }

    protected void _close()
            throws IOException {
    }

    public PrintWriter toPrintWriter() {
        return new PrintOutPrintWriter(this);
    }

}
