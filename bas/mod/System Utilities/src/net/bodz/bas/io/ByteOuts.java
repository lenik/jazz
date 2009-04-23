package net.bodz.bas.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
            public void _write(int b) {
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

    public static class OutputStreamByteOut extends ByteOut {

        protected final OutputStream out;

        public OutputStreamByteOut(OutputStream stream) {
            this.out = stream;
        }

        public OutputStream getOutputStream() {
            return out;
        }

        @Override
        public void write(byte[] buf, int off, int len) throws IOException {
            out.write(buf, off, len);
        }

        @Override
        public void _flush() throws IOException {
            out.flush();
        }

        @Override
        public void _close() throws IOException {
            out.close();
        }

    }

    public static ByteOut get(OutputStream out) {
        return new OutputStreamByteOut(out);
    }

    public static class TempByteOut extends OutputStreamByteOut {

        private final File file;

        public TempByteOut(TempOutputStream out) {
            super(out);
            this.file = out.getFile();
        }

        public TempByteOut(String fileName) throws IOException {
            this(new TempOutputStream(fileName));
        }

        public TempByteOut(File file) throws IOException {
            this(new TempOutputStream(file));
        }

        public File getFile() {
            return file;
        }

    }

    public static class BByteOut extends OutputStreamByteOut {

        public BByteOut() {
            super(new ByteArrayOutputStream());
        }

        public BByteOut(int initialSize) {
            super(new ByteArrayOutputStream(initialSize));
        }

        public ByteArrayOutputStream getBuffer() {
            return (ByteArrayOutputStream) out;
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

        public String toString(String charset) throws UnsupportedEncodingException {
            return getBuffer().toString(charset);
        }

    }

    public static class BBByteOut extends ByteOut {

        private final ByteBuffer bb;

        public BBByteOut(ByteBuffer bb) {
            this.bb = bb;
        }

        @Override
        public void _write(int b) {
            assert b >= Byte.MIN_VALUE && b <= Byte.MAX_VALUE;
            bb.put((byte) b);
        }

        @Override
        public void write(byte[] buf, int off, int len) throws IOException {
            bb.put(buf, off, len);
        }

    }

}
