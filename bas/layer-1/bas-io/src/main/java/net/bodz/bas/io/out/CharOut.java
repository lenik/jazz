package net.bodz.bas.io.out;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import net.bodz.bas.io.PrintException;

public abstract class CharOut implements ICharOut {

    public void _write(CharSequence chars, int off, int len) throws IOException {
        if (chars instanceof String) {
            _write((String) chars, off, len);
            return;
        }
        char[] buf = new char[len];
        for (int i = 0; off < len; i++, off++)
            buf[i] = chars.charAt(off);
        write(buf, 0, len);
    }

    public void _write(String string, int off, int len) throws IOException {
        char[] buf = new char[len];
        string.getChars(off, off + len, buf, 0);
        write(buf, 0, len);
    }

    public void _write(int c) throws IOException {
        char[] cv = new char[1];
        cv[0] = (char) c;
        write(cv, 0, 1);
    }

    private final static char[] NULL = "(null)".toCharArray(); //$NON-NLS-1$
    private final static char NL = '\n';

    public void print(String s) {
        try {
            // char[] chars = s == null ? NULL : s.toCharArray();
            // write(chars, 0, chars.length);
            if (s == null)
                write(NULL, 0, NULL.length);
            else
                _write(s, 0, s.length());
        } catch (IOException e) {
            throw new PrintException(e.getMessage(), e);
        }
    }

    public void print(boolean b) {
        print(String.valueOf(b));
    }

    public void print(char c) {
        try {
            _write(c);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void print(char[] s) {
        assert s != null;
        try {
            write(s, 0, s.length);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void print(double d) {
        print(String.valueOf(d));
    }

    public void print(float f) {
        print(String.valueOf(f));
    }

    public void print(int i) {
        print(String.valueOf(i));
    }

    public void print(long l) {
        print(String.valueOf(l));
    }

    public void print(Object obj) {
        print(String.valueOf(obj));
    }

    public void print(Object... args) {
        for (Object arg : args)
            print(arg);
    }

    public void println() {
        print(NL);
    }

    public void println(boolean x) {
        print(x);
        println();
    }

    public void println(char x) {
        print(x);
        println();
    }

    public void println(char[] x) {
        print(x);
        print("\n"); //$NON-NLS-1$
    }

    public void println(double x) {
        print(x);
        println();
    }

    public void println(float x) {
        print(x);
        println();
    }

    public void println(int x) {
        print(x);
        println();
    }

    public void println(long x) {
        print(x);
        println();
    }

    public void println(Object x) {
        print(x);
        println();
    }

    public void println(String x) {
        print(x);
        println();
    }

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

    public void printf(Locale l, String format, Object... args) {
        print(String.format(l, format, args));
    }

    public void printf(String format, Object... args) {
        print(String.format(format, args));
    }

    public void _flush() throws IOException {
    }

    public void _close() throws IOException {
    }

    public final void flush() {
        try {
            _flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final void close() {
        try {
            _close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class CharOutWriter extends Writer {

        @Override
        public void close() throws IOException {
            CharOut.this._close();
        }

        @Override
        public void flush() throws IOException {
            CharOut.this._flush();
        }

        @Override
        public void write(int c) throws IOException {
            CharOut.this._write(c);
        }

        @Override
        public void write(String str, int off, int len) throws IOException {
            CharOut.this._write(str, off, len);
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            CharOut.this.write(cbuf, off, len);
        }

    }

    public Writer toWriter() {
        return new CharOutWriter();
    }

}
