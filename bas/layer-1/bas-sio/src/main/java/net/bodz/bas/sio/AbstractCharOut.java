package net.bodz.bas.sio;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Locale;

public abstract class AbstractCharOut
        implements ILineCharOut {

    @Override
    public void write(char[] chars)
            throws IOException {
        write(chars, 0, chars.length);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        CharBuffer charBuffer = CharBuffer.wrap(chars, off, off + len);
        write(charBuffer);
    }

    @Override
    public void write(String s)
            throws IOException {
        write(s.toCharArray());
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

    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    @Override
    public void flush()
            throws SIOException {
        try {
            flush(false);
        } catch (IOException e) {
            throw new SIOException();
        }
    }

    private final static char[] NULL = "(null)".toCharArray();
    private final static char NL = '\n';

    @Override
    public void print(String s)
            throws SIOException {
        try {
            // char[] chars = s == null ? NULL : s.toCharArray();
            // write(chars, 0, chars.length);
            if (s == null)
                write(NULL);
            else
                write(s);
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    @Override
    public void print(boolean b)
            throws SIOException {
        print(String.valueOf(b));
    }

    @Override
    public void print(char c)
            throws SIOException {
        try {
            write(c);
        } catch (IOException e) {
            throw new SIOException(e.getMessage(), e);
        }
    }

    @Override
    public void print(char[] s)
            throws SIOException {
        if (s == null)
            throw new NullPointerException("s");
        try {
            write(s, 0, s.length);
        } catch (IOException e) {
            throw new SIOException(e.getMessage(), e);
        }
    }

    @Override
    public void print(double d)
            throws SIOException {
        print(String.valueOf(d));
    }

    @Override
    public void print(float f)
            throws SIOException {
        print(String.valueOf(f));
    }

    @Override
    public void print(int i)
            throws SIOException {
        print(String.valueOf(i));
    }

    @Override
    public void print(long l)
            throws SIOException {
        print(String.valueOf(l));
    }

    @Override
    public void print(Object obj)
            throws SIOException {
        print(String.valueOf(obj));
    }

    @Override
    public void print(Object... args)
            throws SIOException {
        for (Object arg : args)
            print(arg);
    }

    @Override
    public void println()
            throws SIOException {
        try {
            write(NL);
        } catch (IOException e) {
            throw new SIOException(e);
        }
    }

    @Override
    public void println(boolean x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
    public void println(char x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
    public void println(char[] x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
    public void println(double x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
    public void println(float x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
    public void println(int x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
    public void println(long x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
    public void println(Object x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
    public void println(String x)
            throws SIOException {
        print(x);
        println();
    }

    @Override
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

    @Override
    public void printf(Locale l, String format, Object... args)
            throws SIOException {
        print(String.format(l, format, args));
    }

    @Override
    public void printf(String format, Object... args)
            throws SIOException {
        print(String.format(format, args));
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
            AbstractCharOut.this.flush();
        }

        @Override
        public void write(int c)
                throws IOException {
            AbstractCharOut.this.write(c);
        }

        @Override
        public void write(String str, int off, int len)
                throws IOException {
            AbstractCharOut.this.write(str, off, len);
        }

        @Override
        public void write(char[] cbuf, int off, int len)
                throws IOException {
            AbstractCharOut.this.write(cbuf, off, len);
        }

    }

    public Writer toWriter() {
        return new WriterAdapter();
    }

}
