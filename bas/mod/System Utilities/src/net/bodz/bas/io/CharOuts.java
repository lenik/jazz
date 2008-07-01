package net.bodz.bas.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.CharBuffer;

public class CharOuts {

    public static final CharOut stdout = get(System.out);
    public static final CharOut stderr = get(System.err);

    public static CharOut get(final ICharOut out) {
        return new CharOut() {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
                out.write(chars, off, len);
            }
        };
    }

    public static CharOut get(OutputStream out, String charset)
            throws UnsupportedEncodingException {
        return get(new OutputStreamWriter(out, charset), true);
    }

    public static CharOut get(OutputStream out) {
        return get(new OutputStreamWriter(out), true);
    }

    public static CharOut get(final Writer out) {
        return get(out, false);
    }

    public static CharOut get(final Writer out, final boolean autoflush) {
        return new CharOut() {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
                out.write(chars, off, len);
                if (autoflush)
                    out.flush();
            }
        };
    }

    public static CharOut get(final StringBuffer sb) {
        return new CharOut() {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
                sb.append(chars, off, len);
            }
        };
    }

    public static CharOut get(final StringBuilder sb) {
        return new CharOut() {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
                sb.append(chars, off, len);
            }
        };
    }

    public static CharOut get(final CharBuffer cb) {
        return new CharOut() {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
                cb.put(chars, off, len);
            }
        };
    }

    public static class _StringBuffer extends CharOut {

        protected final StringBuffer buffer;

        public _StringBuffer(StringBuffer buffer) {
            this.buffer = buffer;
        }

        public StringBuffer getStringBuffer() {
            return buffer;
        }

        @Override
        public void write(char[] chars, int off, int len) throws IOException {
            buffer.append(chars, off, len);
        }

    }

    public static class Buffer extends _StringBuffer {

        public Buffer() {
            super(new StringBuffer());
        }

        public StringBuffer getBuffer() {
            return buffer;
        }

        public char charAt(int index) {
            return getBuffer().charAt(index);
        }

        public int length() {
            return getBuffer().length();
        }

        @Override
        public String toString() {
            return getBuffer().toString();
        }

    }

}
