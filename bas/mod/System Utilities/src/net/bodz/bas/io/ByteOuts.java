package net.bodz.bas.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ByteOuts {

    public static final ByteOut nil;
    static {
        nil = new ByteOut() {
            @Override
            public void write(byte[] buf, int off, int len) throws IOException {
            }

            @Override
            public void _write(byte b) {
            }

            @Override
            public void _write(byte[] buf, int off, int len) {
            }
        };
    }

    public static final ByteOut stdout = get(System.out);
    public static final ByteOut stderr = get(System.err);

    public static ByteOut get(final IByteOut out) {
        return new ByteOut() {
            @Override
            public void write(byte[] buf, int off, int len) throws IOException {
                out.write(buf, off, len);
            }
        };
    }

    public static ByteOut get(final OutputStream out) {
        return new ByteOut() {
            @Override
            public void write(byte[] buf, int off, int len) throws IOException {
                out.write(buf, off, len);
                out.flush();
            }
        };
    }

    public static ByteOut get(final ByteBuffer out) {
        return new ByteOut() {
            @Override
            public void write(byte[] buf, int off, int len) throws IOException {
                out.put(buf, off, len);
            }
        };
    }

    public static class _OutputStream extends ByteOut {

        protected final OutputStream stream;

        public _OutputStream(OutputStream stream) {
            this.stream = stream;
        }

        public OutputStream getOutputStream() {
            return stream;
        }

        @Override
        public void write(byte[] buf, int off, int len) throws IOException {
            stream.write(buf, off, len);
        }

    }

    public static class Buffer extends _OutputStream {

        public Buffer() {
            super(new ByteArrayOutputStream());
        }

        public Buffer(int initialSize) {
            super(new ByteArrayOutputStream(initialSize));
        }

        public ByteArrayOutputStream getBuffer() {
            return (ByteArrayOutputStream) stream;
        }

        public int size() {
            return getBuffer().size();
        }

        public byte[] toByteArray() {
            return getBuffer().toByteArray();
        }

        @Override
        public String toString() {
            return getBuffer().toString();
        }

        public String toString(String charset)
                throws UnsupportedEncodingException {
            return getBuffer().toString(charset);
        }

    }

}
