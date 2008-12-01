package net.bodz.bas.io;

import java.io.File;
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
            public void _write(int c) throws IOException {
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
        return get(new OutputStreamWriter(out, charset));
    }

    public static CharOut get(OutputStream out) {
        return get(new OutputStreamWriter(out));
    }

    public static class WriterCharOut extends CharOut {

        private final Writer writer;

        public WriterCharOut(Writer out) {
            this.writer = out;
        }

        @Override
        public void write(char[] chars, int off, int len) throws IOException {
            writer.write(chars, off, len);
        }

        @Override
        public void _write(int c) throws IOException {
            writer.write(c);
        }

        @Override
        public void _write(String string, int off, int len) throws IOException {
            writer.write(string, off, len);
        }

        @Override
        public void _flush() throws IOException {
            writer.flush();
        }

        @Override
        public void _close() throws IOException {
            writer.close();
        }

    }

    public static CharOut get(Writer writer) {
        return new WriterCharOut(writer);
    }

    public static class TempCharOut extends WriterCharOut {

        private final File file;

        public TempCharOut(TempWriter writer) {
            super(writer);
            this.file = writer.getFile();
        }

        public TempCharOut(String fileName) throws IOException {
            this(new TempWriter(fileName));
        }

        public TempCharOut(File file) throws IOException {
            this(new TempWriter(file));
        }

        public TempCharOut(String fileName, Object charset) throws IOException {
            this(new TempWriter(fileName, charset));
        }

        public TempCharOut(File file, Object charset) throws IOException {
            this(new TempWriter(file, charset));
        }

        public File getFile() {
            return file;
        }

    }

    public static class Buffer extends CharOut {

        private StringBuffer buffer;

        public Buffer(StringBuffer buffer) {
            this.buffer = buffer;
        }

        public Buffer() {
            this(new StringBuffer());
        }

        public Buffer(int capacity) {
            this(new StringBuffer(capacity));
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
        public void _write(int c) throws IOException {
            buffer.append((char) c);
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

    public static class SBCharOut extends CharOut {

        private final StringBuilder sb;

        public SBCharOut(StringBuilder sb) {
            this.sb = sb;
        }

        @Override
        public void write(char[] chars, int off, int len) throws IOException {
            sb.append(chars, off, len);
        }

        @Override
        public void _write(int c) throws IOException {
            sb.append((char) c);
        }

        @Override
        public void _write(CharSequence chars, int off, int len)
                throws IOException {
            sb.append(chars, off, off + len);
        }
    }

    public static class CBCharOut extends CharOut {

        private final CharBuffer cb;

        public CBCharOut(CharBuffer cb) {
            this.cb = cb;
        }

        @Override
        public void write(char[] chars, int off, int len) throws IOException {
            cb.put(chars, off, len);
        }

        @Override
        public void _write(int c) throws IOException {
            cb.put((char) c);
        }

        @Override
        public void _write(String string, int off, int len) throws IOException {
            cb.put(string, off, len);
        }

    }

}
