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
            throws SIOException {
        try {
            // char[] chars = s == null ? NULL : s.toCharArray();
            // write(chars, 0, chars.length);
            if (s == null)
                write(NULL, 0, NULL.length);
            else
                write(s, 0, s.length());
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    public void print(boolean b)
            throws SIOException {
        print(String.valueOf(b));
    }

    public void print(char c)
            throws SIOException {
        try {
            write(c);
        } catch (IOException e) {
            throw new SIOException(e.getMessage(), e);
        }
    }

    public void print(char[] s)
            throws SIOException {
        assert s != null;
        try {
            write(s, 0, s.length);
        } catch (IOException e) {
            throw new SIOException(e.getMessage(), e);
        }
    }

    public void print(double d)
            throws SIOException {
        print(String.valueOf(d));
    }

    public void print(float f)
            throws SIOException {
        print(String.valueOf(f));
    }

    public void print(int i)
            throws SIOException {
        print(String.valueOf(i));
    }

    public void print(long l)
            throws SIOException {
        print(String.valueOf(l));
    }

    public void print(Object obj)
            throws SIOException {
        print(String.valueOf(obj));
    }

    public void print(Object... args)
            throws SIOException {
        for (Object arg : args)
            print(arg);
    }

    public void println()
            throws SIOException {
        print(NL);
    }

    public void println(boolean x)
            throws SIOException {
        print(x);
        println();
    }

    public void println(char x)
            throws SIOException {
        print(x);
        println();
    }

    public void println(char[] x)
            throws SIOException {
        print(x);
        print("\n");
    }

    public void println(double x)
            throws SIOException {
        print(x);
        println();
    }

    public void println(float x)
            throws SIOException {
        print(x);
        println();
    }

    public void println(int x)
            throws SIOException {
        print(x);
        println();
    }

    public void println(long x)
            throws SIOException {
        print(x);
        println();
    }

    public void println(Object x)
            throws SIOException {
        print(x);
        println();
    }

    public void println(String x)
            throws SIOException {
        print(x);
        println();
    }

    public void println(Object... args)
            throws SIOException {
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
            throws SIOException {
        print(String.format(l, format, args));
    }

    public void printf(String format, Object... args)
            throws SIOException {
        print(String.format(format, args));
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    public void flush()
            throws SIOException {
        try {
            flush(false);
        } catch (IOException e) {
            throw new SIOException();
        }
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
