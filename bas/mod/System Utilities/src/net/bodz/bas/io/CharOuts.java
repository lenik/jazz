package net.bodz.bas.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.CharBuffer;

public class CharOuts {

    public static final CharOut nil;
    static {
        nil = new CharOut() {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
            }

            @Override
            public void _write(char c) throws IOException {
            }

            @Override
            public void _write(CharSequence chars, int off, int len)
                    throws IOException {
            }

            @Override
            public void _write(String string, int off, int len)
                    throws IOException {
            }
        };
    }

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

            @Override
            public void _write(char c) throws IOException {
                out.write(c);
                if (autoflush)
                    out.flush();
            }

            @Override
            public void _write(String string, int off, int len)
                    throws IOException {
                out.write(string, off, len);
                if (autoflush)
                    out.flush();
            }

        };
    }

    public static Buffer get(final StringBuffer sb) {
        return new Buffer(sb);
    }

    public static CharOut get(final StringBuilder sb) {
        return new CharOut() {
            @Override
            public void write(char[] chars, int off, int len)
                    throws IOException {
                sb.append(chars, off, len);
            }

            @Override
            public void _write(char c) throws IOException {
                sb.append(c);
            }

            @Override
            public void _write(CharSequence chars, int off, int len)
                    throws IOException {
                sb.append(chars, off, off + len);
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

            @Override
            public void _write(char c) throws IOException {
                cb.put(c);
            }

            @Override
            public void _write(String string, int off, int len)
                    throws IOException {
                cb.put(string, off, len);
            }
        };
    }

    public static class Buffer extends CharOut {

        private StringBuffer buffer;

        public Buffer(StringBuffer buffer) {
            this.buffer = buffer;
        }

        public Buffer() {
            this(new StringBuffer());
        }

        public StringBuffer getBuffer() {
            return buffer;
        }

        public void setBuffer(StringBuffer buffer) {
            assert buffer != null;
            this.buffer = buffer;
        }

        @Override
        public void write(char[] chars, int off, int len) throws IOException {
            buffer.append(chars, off, len);
        }

        @Override
        public void _write(char c) throws IOException {
            buffer.append(c);
        }

        @Override
        public void _write(CharSequence chars, int off, int len)
                throws IOException {
            buffer.append(chars, off, off + len);
        }

        public char charAt(int index) {
            return buffer.charAt(index);
        }

        public int length() {
            return buffer.length();
        }

        @Override
        public String toString() {
            return buffer.toString();
        }

    }

}
