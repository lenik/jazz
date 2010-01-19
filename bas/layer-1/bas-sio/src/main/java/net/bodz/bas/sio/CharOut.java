package net.bodz.bas.sio;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Locale;

public abstract class CharOut
        implements ICharOut {

    @Override
    public void write(int c)
            throws IOException {
        char[] cv = new char[1];
        cv[0] = (char) c;
        write(cv, 0, 1);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        CharBuffer charBuffer = CharBuffer.wrap(chars, off, off + len);
        write(charBuffer);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        char[] buf = new char[len];
        string.getChars(off, off + len, buf, 0);
        write(buf, 0, len);
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        if (charBuffer == null)
            throw new NullPointerException("charBuffer");
        char[] array = charBuffer.array();
        int offset = charBuffer.arrayOffset();
        int limit = charBuffer.limit();
        write(array, offset, limit);
    }

    private final static char[] NULL = "(null)".toCharArray();
    private final static char NL = '\n';

    public void print(String s)
            throws IOException {
        // char[] chars = s == null ? NULL : s.toCharArray();
        // write(chars, 0, chars.length);
        if (s == null)
            write(NULL, 0, NULL.length);
        else
            write(s, 0, s.length());
    }

    public void print(boolean b)
            throws IOException {
        print(String.valueOf(b));
    }

    public void print(char c)
            throws IOException {
        write(c);
    }

    public void print(char[] s)
            throws IOException {
        assert s != null;
        write(s, 0, s.length);
    }

    public void print(double d)
            throws IOException {
        print(String.valueOf(d));
    }

    public void print(float f)
            throws IOException {
        print(String.valueOf(f));
    }

    public void print(int i)
            throws IOException {
        print(String.valueOf(i));
    }

    public void print(long l)
            throws IOException {
        print(String.valueOf(l));
    }

    public void print(Object obj)
            throws IOException {
        print(String.valueOf(obj));
    }

    public void print(Object... args)
            throws IOException {
        for (Object arg : args)
            print(arg);
    }

    public void println()
            throws IOException {
        print(NL);
    }

    public void println(boolean x)
            throws IOException {
        print(x);
        println();
    }

    public void println(char x)
            throws IOException {
        print(x);
        println();
    }

    public void println(char[] x)
            throws IOException {
        print(x);
        print("\n");
    }

    public void println(double x)
            throws IOException {
        print(x);
        println();
    }

    public void println(float x)
            throws IOException {
        print(x);
        println();
    }

    public void println(int x)
            throws IOException {
        print(x);
        println();
    }

    public void println(long x)
            throws IOException {
        print(x);
        println();
    }

    public void println(Object x)
            throws IOException {
        print(x);
        println();
    }

    public void println(String x)
            throws IOException {
        print(x);
        println();
    }

    public void println(Object... args)
            throws IOException {
        if (args.length == 0) {
            println();
            return;
        }
        int max = args.length - 1;
        for (int i = 0; i < max; i++)
            print(args[i]);
        println(args[max]);
    }

    public void printf(Locale l, String format, Object... args)
            throws IOException {
        print(String.format(l, format, args));
    }

    public void printf(String format, Object... args)
            throws IOException {
        print(String.format(format, args));
    }

    public void flush()
            throws IOException {
    }

    class WriterAdapter
            extends Writer {

        @Override
        public void close()
                throws IOException {
        }

        @Override
        public void flush()
                throws IOException {
            CharOut.this.flush();
        }

        @Override
        public void write(int c)
                throws IOException {
            CharOut.this.write(c);
        }

        @Override
        public void write(String str, int off, int len)
                throws IOException {
            CharOut.this.write(str, off, len);
        }

        @Override
        public void write(char[] cbuf, int off, int len)
                throws IOException {
            CharOut.this.write(cbuf, off, len);
        }

    }

    public Writer toWriter() {
        return new WriterAdapter();
    }

    public static final CharOut nil = NullCharOut.getInstance();
    public static final CharOut stdout = new PrintStreamCharOut(System.out);
    public static final CharOut stderr = new PrintStreamCharOut(System.err);

}
