package net.bodz.bas.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.Manifest;
import java.util.regex.Pattern;

import net.bodz.bas.collection.iterator.PrefetchedIterator;
import net.bodz.bas.exceptions.IdentifiedException;
import net.bodz.bas.exceptions.IllegalArgumentTypeException;
import net.bodz.bas.exceptions.RuntimizedException;
import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.exceptions.WrappedException;
import net.bodz.bas.io.res.FileResFolder;
import net.bodz.bas.io.res.ResFolder;
import net.bodz.bas.io.res.URLResFolder;
import net.bodz.bas.io.res.ZipResFolder;
import net.bodz.bas.primitive.Bits;
import net.bodz.bas.primitive.IntMath;
import sun.dyn.empty.Empty;

/**
 * @test {@link FilesTest}
 */
public class Files {

    public static int blockSize = 4096;
    private static String slash;
    static {
        slash = System.getProperty("file.separator"); //$NON-NLS-1$
        if (slash == null)
            slash = "/"; //$NON-NLS-1$
    }

    // adapters

    /**
     * @param in
     *            type of one of following types:
     *            <ul>
     *            <li><code>InputStream</code>
     *            <li><code>Reader</code> -> {@link ReaderInputStream}
     *            <li><code>File</code> -> {@link FileInputStream}
     *            <li><code>String</code> -> {@link FileInputStream}
     *            <li><code>URI</code>-> {@link URL#openStream()}
     *            <li><code>URL</code> -> {@link URL#openStream()}
     *            <li><code>byte[]</code> -> {@link ByteArrayInputStream}
     *            </ul>
     */
    public static InputStream getInputStream(Object in, Object charset) throws IOException {
        if (in == null)
            return null;
        if (in instanceof InputStream)
            return (InputStream) in;
        if (in instanceof Reader)
            return new ReaderInputStream((Reader) in, Charsets.get(charset));
        if (in instanceof File)
            return new FileInputStream((File) in);
        if (in instanceof String) // filename
            return new FileInputStream((String) in);
        if (in instanceof URL)
            return ((URL) in).openStream();
        if (in instanceof URI)
            return ((URI) in).toURL().openStream();
        if (in instanceof byte[])
            return new ByteArrayInputStream((byte[]) in);
        throw new IllegalArgumentTypeException(in);
    }

    /**
     * @see #getInputStream(Object, Object)
     */
    public static InputStream getInputStream(Object in) throws IOException {
        return getInputStream(in, Charsets.DEFAULT);
    }

    /**
     * @param out
     *            type of one of following types:
     *            <ul>
     *            <li><code>OutputStream</code>
     *            <li><code>Writer</code> -> {@link WriterOutputStream}
     *            <li><code>File</code> -> {@link FileOutputStream}
     *            <li><code>String</code> -> {@link FileOutputStream}
     *            <li><code>URI</code>-> {@link File#File(URI)}
     *            <li><code>URL</code> -> {@link File#File(URI)}
     *            </ul>
     */
    public static OutputStream getOutputStream(Object out, Object charset, boolean append) throws IOException {
        if (out == null)
            return null;
        if (out instanceof OutputStream)
            return (OutputStream) out;
        if (out instanceof Writer)
            return new WriterOutputStream((Writer) out, Charsets.get(charset));
        if (out instanceof File)
            return new FileOutputStream((File) out, append);
        if (out instanceof String)
            return new FileOutputStream((String) out, append);
        if (out instanceof URL)
            try {
                return new FileOutputStream(new File(((URL) out).toURI()), append);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        if (out instanceof URI)
            return new FileOutputStream(new File((URI) out), append);
        throw new IllegalArgumentTypeException(out);
    }

    /**
     * @see #getOutputStream(Object, Object, boolean)
     */
    public static OutputStream getOutputStream(Object out, boolean append) throws IOException {
        return getOutputStream(out, Charsets.DEFAULT, append);
    }

    /**
     * @see #getOutputStream(Object, Object, boolean)
     */
    public static OutputStream getOutputStream(Object out, Object charset) throws IOException {
        return getOutputStream(out, charset, false);
    }

    /**
     * @see #getOutputStream(Object, Object, boolean)
     */
    public static OutputStream getOutputStream(Object out) throws IOException {
        return getOutputStream(out, Charsets.DEFAULT, false);
    }

    /**
     * @param in
     *            type of one of following types, or any of {@link #getInputStream(Object, Object)}
     *            thru {@link InputStreamReader}.
     *            <ul>
     *            <li><code>Reader</code>
     *            <li><code>String</code> -> {@link StringReader}
     *            <li><code>char[]</code> -> {@link StringReader}
     *            </ul>
     */
    public static Reader getReader(Object in, Object charset) throws IOException {
        if (in == null)
            return null;
        if (in instanceof Reader)
            return (Reader) in;
        if (in instanceof String)
            return new StringReader((String) in);
        if (in instanceof char[])
            return new StringReader(new String((char[]) in));
        InputStream ins = getInputStream(in);
        return new InputStreamReader(ins, Charsets.get(charset));
    }

    /**
     * @see #getReader(Object, Object)
     */
    public static Reader getReader(Object in) throws IOException {
        return getReader(in, Charsets.DEFAULT);
    }

    public static BufferedReader getBufferedReader(Object in, Object charset) throws IOException {
        if (in == null)
            return null;
        if (in instanceof BufferedReader)
            return (BufferedReader) in;
        return new BufferedReader(getReader(in, charset));
    }

    public static BufferedReader getBufferedReader(Object in) throws IOException {
        return getBufferedReader(in, Charsets.DEFAULT);
    }

    public static LineReader getLineReader(Object in, Object charset) throws IOException {
        if (in == null)
            return null;
        if (in instanceof LineReader)
            return (LineReader) in;
        return new LineReader(getReader(in, charset));
    }

    public static LineReader getLineReader(Object in) throws IOException {
        return getLineReader(in, Charsets.DEFAULT);
    }

    /**
     * @param out
     *            type of one of following types, or any of {@link #getOutputStream(Object, Object)}
     *            thru {@link OutputStreamWriter}.
     *            <ul>
     *            <li><code>Writer</code>
     *            </ul>
     */
    public static Writer getWriter(Object out, Object charset, boolean append) throws IOException {
        if (out == null)
            return null;
        if (out instanceof Writer)
            return (Writer) out;
        OutputStream outs = getOutputStream(out, append);
        return new OutputStreamWriter(outs, Charsets.get(charset));
    }

    public static Writer getWriter(Object out, boolean append) throws IOException {
        return getWriter(out, Charsets.DEFAULT, append);
    }

    public static Writer getWriter(Object out, Object charset) throws IOException {
        return getWriter(out, charset, false);
    }

    public static Writer getWriter(Object out) throws IOException {
        return getWriter(out, Charsets.DEFAULT, false);
    }

    public static boolean shouldClose(Object io) {
        if (io instanceof File)
            return true;
        if (io instanceof String)
            return true;
        return false;
    }

    public static byte[] readBytes(Object in, long size, Object charset, boolean close) throws IOException {
        InputStream ins = getInputStream(in, charset);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte block[] = new byte[blockSize];
        try {
            while (size != 0) {
                int readSize = block.length;
                if (size != -1 && readSize > size)
                    readSize = (int) size;
                int n = ins.read(block, 0, readSize);
                if (n == -1)
                    break;
                if (size != -1)
                    size -= n;
                buffer.write(block, 0, n);
            }
        } finally {
            if (close)
                ins.close();
        }
        return buffer.toByteArray();

    }

    public static byte[] readBytes(Object in, long size, Object charset) throws IOException {
        boolean close = shouldClose(in);
        return readBytes(in, size, close);
    }

    public static byte[] readBytes(Object in, long size, boolean close) throws IOException {
        return readBytes(in, size, Charsets.DEFAULT, close);
    }

    public static byte[] readBytes(Object in, long size) throws IOException {
        return readBytes(in, size, Charsets.DEFAULT);
    }

    public static byte[] readBytes(Object in, Object charset, boolean close) throws IOException {
        return readBytes(in, -1, charset, close);
    }

    public static byte[] readBytes(Object in, Object charset) throws IOException {
        boolean close = shouldClose(in);
        return readBytes(in, close);
    }

    public static byte[] readBytes(Object in, boolean close) throws IOException {
        return readBytes(in, Charsets.DEFAULT, close);
    }

    public static byte[] readBytes(Object in) throws IOException {
        return readBytes(in, Charsets.DEFAULT);
    }

    public static String readAll(Object in, Object charset, boolean close) throws IOException {
        Reader reader = getReader(in, charset);
        StringBuffer buffer = new StringBuffer();
        try {
            while (true) {
                int c = reader.read();
                if (c == -1)
                    break;
                buffer.append((char) c);
            }
        } finally {
            if (close)
                reader.close();
        }
        return buffer.toString();
    }

    public static String readAll(Object in, Object charset) throws IOException {
        boolean close = shouldClose(in);
        return readAll(in, charset, close);
    }

    public static String readAll(Object in, boolean close) throws IOException {
        return readAll(in, Charsets.DEFAULT, close);
    }

    public static String readAll(Object in) throws IOException {
        return readAll(in, Charsets.DEFAULT);
    }

    /**
     * @return each line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static List<String> readLines(Object in, Object charset, boolean close) throws IOException {
        LineReader reader = getLineReader(in, charset);
        List<String> lines = new ArrayList<String>();
        String line;
        try {
            while ((line = reader.readLine()) != null)
                lines.add(line);
        } finally {
            if (close)
                reader.close();
        }
        return lines;
    }

    /**
     * @return each line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static List<String> readLines(Object in, Object charset) throws IOException {
        boolean close = shouldClose(in);
        return readLines(in, charset, close);
    }

    /**
     * @return each line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static List<String> readLines(Object in, boolean close) throws IOException {
        return readLines(in, Charsets.DEFAULT, close);
    }

    /**
     * @return each line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static List<String> readLines(Object in) throws IOException {
        return readLines(in, Charsets.DEFAULT);
    }

    /**
     * @return line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static String readLine(Object in, Object charset, boolean close, int index, Pattern pattern)
            throws IOException {
        assert index > 0;
        LineReader reader = getLineReader(in, charset);
        String line = null;
        try {
            while (--index >= 0) {
                if ((line = reader.readLine()) == null)
                    break; // EOF
                if (pattern != null)
                    if (!pattern.matcher(line).matches())
                        continue;
            }
        } finally {
            if (close)
                reader.close();
        }
        return line;
    }

    /**
     * @return line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static String readLine(Object in, Object charset, boolean close) throws IOException {
        return readLine(in, charset, close, 1, null);
    }

    /**
     * @return line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static String readLine(Object in, Object charset) throws IOException {
        boolean close = shouldClose(in);
        return readLine(in, charset, close);
    }

    /**
     * @return line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static String readLine(Object in, boolean close) throws IOException {
        return readLine(in, Charsets.DEFAULT, close);
    }

    /**
     * @return line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static String readLine(Object in) throws IOException {
        return readLine(in, Charsets.DEFAULT);
    }

    public static String readTill(Object in, char term, Charset charset, boolean close) throws IOException {
        Reader reader = getReader(in, charset);
        StringBuffer buffer = new StringBuffer();
        int c;
        try {
            while ((c = reader.read()) >= 0) {
                if (c == term)
                    break;
                buffer.append((char) c);
            }
        } finally {
            if (close)
                reader.close();
        }
        return buffer.toString();
    }

    public static String readTill(Object in, char term, Charset charset) throws IOException {
        boolean close = shouldClose(in);
        return readTill(in, term, charset, close);
    }

    public static String readTill(Object in, char term, boolean close) throws IOException {
        return readTill(in, term, Charsets.DEFAULT, close);
    }

    public static String readTill(Object in, char term) throws IOException {
        return readTill(in, term, Charsets.DEFAULT);
    }

    public static String readLen(Object in, int length, Charset charset, boolean close) throws IOException {
        Reader reader = getReader(in, charset);
        try {
            char[] buf = new char[length];
            reader.read(buf);
            return new String(buf);
        } finally {
            if (close)
                reader.close();
        }
    }

    public static String readLen(Object in, int length, Charset charset) throws IOException {
        boolean close = shouldClose(in);
        return readLen(in, length, charset, close);
    }

    public static String readLen(Object in, int length, boolean close) throws IOException {
        return readLen(in, length, Charsets.DEFAULT, close);
    }

    public static String readLen(Object in, int length) throws IOException {
        return readLen(in, length, Charsets.DEFAULT);
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     */
    protected static Iterator<Integer> _readByBlock(final Object[] files, final byte[] buffer) {
        assert files != null : "null files[]"; //$NON-NLS-1$
        assert buffer != null : "null buffer"; //$NON-NLS-1$

        return new PrefetchedIterator<Integer>() {

            private int index = -1;
            private InputStream input = null;
            private boolean close;

            @_throws(IOException.class)
            @Override
            public Integer fetch() {
                try {
                    if (index >= files.length)
                        return end();
                    if (input == null)
                        nextFile();
                    int len = input.read(buffer);
                    if (len == -1) {
                        nextFile();
                        return fetch();
                    }
                    return len;
                } catch (IOException e) {
                    throw new RuntimizedException(e.getMessage(), e);
                }
            }

            protected boolean nextFile() throws IOException {
                if (input != null && close)
                    input.close();
                if (++index >= files.length)
                    return false;
                input = getInputStream(files[index]);
                close = shouldClose(files[index]);
                return true;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     */
    public static Iterable<Integer> readByBlock(final byte[] buffer, final Object... files) {
        return new Iterable<Integer>() {

            @Override
            public Iterator<Integer> iterator() {
                return _readByBlock(files, buffer);
            }

        };
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     */
    public static Iterable<byte[]> readByBlock(final int blockSize, final Object... files) {
        final byte[] buffer = new byte[blockSize];
        return new Iterable<byte[]>() {
            @Override
            public Iterator<byte[]> iterator() {
                return new Iterator<byte[]>() {
                    final Iterator<Integer> it = _readByBlock(files, buffer);

                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public byte[] next() {
                        int len = it.next();
                        if (len == blockSize)
                            return buffer;
                        if (len == -1)
                            return null;
                        byte[] spec = new byte[len];
                        System.arraycopy(buffer, 0, spec, 0, len);
                        return spec;
                    }

                    @Override
                    public void remove() {
                        it.remove();
                    }
                };
            }
        };
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     */
    public static Iterable<byte[]> readByBlock(final Object... files) {
        return readByBlock(blockSize, files);
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     */
    protected static Iterator<Integer> _readByLen(final Object[] files, final char[] buffer) {
        assert files != null : "null files[]"; //$NON-NLS-1$
        assert buffer != null : "null buffer"; //$NON-NLS-1$

        return new PrefetchedIterator<Integer>() {

            private int index = -1;
            private Reader input = null;
            private boolean close;

            @_throws(IOException.class)
            @Override
            public Integer fetch() {
                try {
                    if (index >= files.length)
                        return end();
                    if (input == null)
                        nextFile();
                    int len = input.read(buffer);
                    if (len == -1) {
                        nextFile();
                        return fetch();
                    }
                    return len;
                } catch (IOException e) {
                    throw new RuntimizedException(e.getMessage(), e);
                }
            }

            protected boolean nextFile() throws IOException {
                if (input != null && close)
                    input.close();
                if (++index >= files.length)
                    return false;
                input = getReader(files[index]);
                close = shouldClose(files[index]);
                return true;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     */
    public static Iterable<Integer> readByLen(final char[] buffer, final Object... files) {
        return new Iterable<Integer>() {

            @Override
            public Iterator<Integer> iterator() {
                return _readByLen(files, buffer);
            }

        };
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     */
    public static Iterable<char[]> readByLen(final int blockSize, final Object... files) {
        final char[] buffer = new char[blockSize];
        final Iterator<Integer> it = _readByLen(files, buffer);
        return new Iterable<char[]>() {

            @Override
            public Iterator<char[]> iterator() {
                return new Iterator<char[]>() {

                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public char[] next() {
                        int len = it.next();
                        if (len == blockSize)
                            return buffer;
                        if (len == -1)
                            return null;
                        char[] spec = new char[len];
                        System.arraycopy(buffer, 0, spec, 0, len);
                        return spec;
                    }

                    @Override
                    public void remove() {
                        it.remove();
                    }

                };
            }

        };
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     */
    public static Iterable<char[]> readByLen(final Object... files) {
        return readByLen(blockSize, files);
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     * 
     * @return iterated line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    protected static Iterator<String> _readByLine(final Object[] files, final Charset charset) {
        return new PrefetchedIterator<String>() {

            private int fileIndex = 0;
            private LineReader reader = null;

            @_throws(IOException.class)
            @Override
            public String fetch() {
                if (fileIndex >= files.length)
                    return end();
                try {
                    if (reader == null) {
                        Object in = files[fileIndex];
                        assert in != null : "null file"; //$NON-NLS-1$
                        in = getLineReader(in, charset);
                        reader = (LineReader) in;
                    }
                    String line = reader.readLine();
                    if (line == null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                        }
                        fileIndex++;
                        return fetch();
                    }
                    return line;
                } catch (IOException e) {
                    throw new RuntimizedException(e.getMessage(), e);
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     * 
     * @return iterated line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static Iterable<String> readByLine(final Object... files) {
        return new Iterable<String>() {

            @Override
            public Iterator<String> iterator() {
                return _readByLine(files, Charsets.DEFAULT);
            }

        };
    }

    /**
     * {@link Iterator#next()} throws {@link RuntimizedException}.
     * 
     * @return iterated line includes line term chars.
     * 
     * @see LineReader#readLine()
     */
    public static Iterable<String> readByLine2(final Object charset, final Object... files) {
        return new Iterable<String>() {

            @Override
            public Iterator<String> iterator() {
                return _readByLine(files, Charsets.get(charset));
            }

        };
    }

    // loadProperties

    public static Properties loadProperties(Object in, Object charset, boolean close) throws IOException {
        if (in == null)
            throw new NullPointerException("in");
        Reader reader = getReader(in, charset);
        try {
            Properties properties = new Properties();
            properties.load(reader);
            return properties;
        } finally {
            if (close)
                reader.close();
        }
    }

    public static Properties loadProperties(Object in, Object charset) throws IOException {
        boolean close = shouldClose(in);
        return loadProperties(in, charset, close);
    }

    public static Properties loadProperties(Object in, boolean close) throws IOException {
        return loadProperties(in, Charsets.DEFAULT, close);
    }

    public static Properties loadProperties(Object in) throws IOException {
        return loadProperties(in, Charsets.DEFAULT);
    }

    // printTo

    public static PrintStream printTo(Object out, Object charset) throws IOException {
        boolean autoFlush = false;
        String cs = Charsets.get(charset).name();
        return new PrintStream(getOutputStream(out), autoFlush, cs);
    }

    public static PrintStream printTo(Object out) throws IOException {
        return printTo(out, Charsets.DEFAULT);
    }

    // outputTo

    public static OutputStream outputTo(Object out, Object charset) throws IOException {
        String cs = Charsets.get(charset).name();
        return getOutputStream(out, cs);
    }

    public static OutputStream outputTo(Object out) throws IOException {
        return getOutputStream(out);
    }

    // writeTo

    public static Writer writeTo(Object out, Object charset) throws IOException {
        String cs = Charsets.get(charset).name();
        return getWriter(out, cs);
    }

    public static Writer writeTo(Object out) throws IOException {
        return getWriter(out);
    }

    // write

    public static <T> void write(Object out, T data, Object charset, boolean append) throws IOException {
        boolean close = shouldClose(out);
        if (data instanceof byte[]) {
            OutputStream outs = getOutputStream(out, charset, append);
            outs.write((byte[]) data);
            if (close)
                outs.close();
        } else {
            Writer writer = getWriter(out, charset, append);
            if (data instanceof char[])
                writer.write((char[]) data);
            else
                writer.write(String.valueOf(data));
            if (close)
                writer.close();
        }
    }

    public static <T> void write(Object out, T data, Object charset) throws IOException {
        write(out, data, charset, false);
    }

    public static <T> void write(Object out, T data) throws IOException {
        write(out, data, Charsets.DEFAULT);
    }

    public static <T> void write(Object out, byte[] data, int off, int len) throws IOException {
        OutputStream outs = getOutputStream(out);
        boolean close = shouldClose(out);
        outs.write(data, off, len);
        if (close)
            outs.close();
    }

    public static PrintStream appendTo(Object out, Object charset) throws IOException {
        if (out instanceof File)
            return appendTo(new FileOutputStream((File) out, true), charset);
        if (out instanceof String)
            return appendTo(new FileOutputStream((String) out, true), charset);
        OutputStream outs = getOutputStream(out);
        boolean autoFlush = true;
        String cs = Charsets.get(charset).name();
        return new PrintStream(outs, autoFlush, cs);
    }

    public static PrintStream appendTo(Object out) throws IOException {
        return appendTo(out, Charsets.DEFAULT);
    }

    public static <T> void append(Object out, T data, Object charset) throws IOException {
        write(out, data, charset, true);
    }

    public static <T> void append(Object out, T data) throws IOException {
        write(out, data, Charsets.DEFAULT, true);
    }

    public static <T> void append(Object out, byte[] data, int off, int len) throws IOException {
        OutputStream outs = getOutputStream(out, true);
        boolean close = shouldClose(out);
        outs.write(data, off, len);
        if (close)
            outs.close();
    }

    static void _mkdir_p(File file) {
        if (file == null)
            throw new NullPointerException("fille"); //$NON-NLS-1$
        File parentFile = file.getParentFile();
        parentFile = Files.canoniOf(parentFile);
        parentFile.mkdirs();
    }

    public static <T> void createFile(File file, T data, Object charset) throws IOException {
        _mkdir_p(file);
        write(file, data, charset);
    }

    public static <T> void createFile(File file, T data) throws IOException {
        _mkdir_p(file);
        write(file, data, Charsets.DEFAULT);
    }

    public static <T> void createFile(File file, byte[] data, int off, int len) throws IOException {
        if (file == null)
            throw new NullPointerException("file"); //$NON-NLS-1$
        _mkdir_p(file);
        write(file, data, off, len);
    }

    // _load

    /**
     * @return {@link Iterable} whose {@link Iterator#next()} throws {@link WrappedException} of
     *         {@link IOException}
     */
    public static Iterable<Object> _load(final Object in) {

        class ObjIter extends PrefetchedIterator<Object> {
            final ObjectInput objin;
            boolean shouldClose;

            public ObjIter() throws IOException {
                if (in instanceof ObjectInput)
                    objin = (ObjectInput) in;
                else
                    objin = new ObjectInputStream(getInputStream(in));
                shouldClose = shouldClose(in);
            }

            @Override
            protected Object fetch() {
                if (objin == null)
                    return end();
                try {
                    Object x = objin.readObject();
                    return x;
                } catch (EOFException e) {
                    if (shouldClose)
                        try {
                            objin.close();
                        } catch (IOException closeEx) {
                            throw new WrappedException(closeEx);
                        }
                    return end();
                } catch (ClassNotFoundException e) {
                    throw new WrappedException(e);
                } catch (IOException e) {
                    throw new WrappedException(e);
                }
            }

            @Override
            protected void finalize() throws Throwable {
                if (shouldClose && objin != null)
                    objin.close();
            }
        }

        return new Iterable<Object>() {
            @Override
            public Iterator<Object> iterator() {
                try {
                    return new ObjIter();
                } catch (IOException e) {
                    throw new WrappedException(e);
                }
            }
        };
    }

    public static Object[] loadAll(Object in) throws IOException {
        List<Object> buf = new ArrayList<Object>();
        try {
            for (Object o : _load(in)) {
                buf.add(o);
            }
        } catch (WrappedException e) {
            e.rethrow(IOException.class);
        }
        return buf.toArray();
    }

    public static Object load(Object in, int index) throws IOException {
        assert index >= 0 : "Invalid index " + index; //$NON-NLS-1$
        try {
            for (Object o : _load(in)) {
                if (index-- == 0)
                    return o;
            }
        } catch (WrappedException e) {
            e.rethrow(IOException.class);
        }
        throw new IndexOutOfBoundsException(SysNLS.getString("Files.scanOverEnd") + index); //$NON-NLS-1$
    }

    public static Object load(Object in) throws IOException {
        return load(in, 0);
    }

    // data associated with a class

    /**
     * @return <code>null</code> if no manifest.
     */
    public static Manifest getManifest(Class<?> clazz) {
        URL url = clazz.getResource("/META-INF/MANIFEST.MF"); //$NON-NLS-1$
        if (url == null)
            return null;
        InputStream in = null;
        try {
            in = url.openStream();
            Manifest manifest = new Manifest(in);
            in.close();
            return manifest;
        } catch (IOException e) {
            throw new IllegalStateException(SysNLS.getString("Files.badManifest") + clazz, e); //$NON-NLS-1$
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    /**
     * Same as {@link #_classData(Class, String)} with ".class" as <code>extension</code>.
     */
    public static URL classData(Class<?> clazz) {
        String thisName = clazz.getSimpleName();
        return clazz.getResource(thisName + ".class"); //$NON-NLS-1$
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URL classData(Class<?> clazz, String extension) {
        return _classData(clazz, "." + extension); //$NON-NLS-1$
    }

    /**
     * @param extension
     *            must include the dot(.), if necessary
     */
    public static URL _classData(Class<?> clazz, String extension) {
        String thisName = clazz.getSimpleName();
        // the resource may not exist.
        // clazz.getResource(thisName + extension);
        URL self = classData(clazz);
        String spec = thisName + extension;
        try {
            URL url = new URL(self, spec);
            return url;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static URL getRootResource(Class<?> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        if (classLoader == null)
            throw new NullPointerException("can't getClassLoader"); //$NON-NLS-1$
        String pkg = clazz.getPackage().getName();
        String base = clazz.getSimpleName() + ".class"; //$NON-NLS-1$
        String name = pkg.replace('.', '/') + '/' + base;
        return getRootResource(classLoader, name);
    }

    public static URL getRootResource(ClassLoader classLoader, String hintPath) {
        hintPath = hintPath.replace('\\', '/');
        URL url = classLoader.getResource(hintPath);
        String s = url.toString();
        if (!s.endsWith(hintPath))
            throw new UnexpectedException(SysNLS.getString("Files.gotBadResURL")); //$NON-NLS-1$
        s = s.substring(0, s.length() - hintPath.length());
        try {
            url = new URL(s);
        } catch (MalformedURLException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
        if (url == null)
            throw new Error(SysNLS.getString("Files.cantGetRootRes")); //$NON-NLS-1$
        return url;
    }

    // dump

    public static void dump(Object out, Object... objs) throws IOException {
        assert out != null;
        ObjectOutput objout = (out instanceof ObjectOutput) ? (ObjectOutput) out : new ObjectOutputStream(
                getOutputStream(out));
        boolean close = shouldClose(out);
        for (Object o : objs)
            objout.writeObject(o);
        if (close)
            objout.close();
    }

    public static byte[] dumpBytes(Object... objs) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        dump(buf, objs);
        return buf.toByteArray();
    }

    // Path functions

    /**
     * This canonical form is safe to getParentFile().
     */
    public static File canoniOf(File f) {
        if (f == null)
            return null;
        try {
            return f.getCanonicalFile();
        } catch (Exception e) {
            return f.getAbsoluteFile();
        }
    }

    /**
     * @see CWD#get(String)
     */
    public static File canoniOf(String path) throws NullPointerException {
        return canoniOf(new File(path));
    }

    public static File canoniOf(URI uri) throws NullPointerException {
        if (uri == null)
            throw new NullPointerException();
        return canoniOf(new File(uri));
    }

    public static File canoniOf(URL url) throws NullPointerException {
        if (url == null)
            throw new NullPointerException();
        try {
            return canoniOf(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public static File canoniOf(Object o) {
        if (o == null)
            return null;
        if (o instanceof File)
            return canoniOf((File) o);
        if (o instanceof String)
            return canoniOf((String) o);
        if (o instanceof URI)
            return canoniOf((URI) o);
        if (o instanceof URL)
            return canoniOf((URL) o);
        throw new IllegalArgumentTypeException(o);
    }

    public static File canoniOf(Object parent, String child) {
        File _parent = canoniOf(parent);
        return canoniOf(new File(_parent, child));
    }

    public static URI getURI(String path) {
        if (path == null)
            return null;
        URI uri = new File(path).toURI();
        return uri;
    }

    public static URI getURI(File file) {
        if (file == null)
            return null;
        file = canoniOf(file);
        return file.toURI();
    }

    public static URL getURL(String path) throws MalformedURLException {
        if (path == null)
            return null;
        return getURI(path).toURL();
    }

    public static URL getURL(File file) {
        return getURL(file, true);
    }

    public static URL getURL(File file, boolean canonical) {
        if (file == null)
            return null;
        if (canonical)
            file = canoniOf(file);
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    static File getJarFile(URL jarURL) {
        assert "jar".equals(jarURL.getProtocol()); //$NON-NLS-1$
        String s = jarURL.getPath(); // path = file:/...!...
        assert s.startsWith("file:"); //$NON-NLS-1$
        int excl = s.lastIndexOf('!');
        if (excl != -1) // assert
            s = s.substring(0, excl); // path = file:/...
        try {
            URL truncatedURL = new URL(s);
            return canoniOf(truncatedURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * if url is an entry of a jar file, then the jar file is returned.
     * 
     * @return the accessible file part of url
     */
    public static File getFile(URL url) throws MalformedURLException {
        if (url == null)
            return null;
        String protocol = url.getProtocol();
        if ("jar".equals(protocol)) //$NON-NLS-1$
            return getJarFile(url);
        return canoniOf(url);
    }

    public static File getFile(URL url, String removeSubPath) throws MalformedURLException {
        ResFolder resFolder = getResFolder(url, removeSubPath);
        if (resFolder instanceof FileResFolder)
            return ((FileResFolder) resFolder).getFile();
        if (resFolder instanceof ZipResFolder)
            return ((ZipResFolder) resFolder).getFile();
        if (resFolder instanceof URLResFolder) {
            URL context = ((URLResFolder) resFolder).getContext();
            return Files.getFile(context);
        }
        throw new UnsupportedOperationException("Can't get file from ResFolder " + resFolder); //$NON-NLS-1$
    }

    public static ResFolder getResFolder(URL url, String removeSubPath) throws MalformedURLException {
        if (removeSubPath == null || removeSubPath.isEmpty())
            return new URLResFolder(url);
        // jar:file:/C:/abc/dir/example.jar!/com/example/Name.class
        // String _url = url.toExternalForm();

        // s=file:/C:/abc/dir/example.jar!/com/example/Name.class
        String s = url.toExternalForm();
        if (!s.endsWith(removeSubPath)) {
            throw new IllegalArgumentException(String.format(SysNLS.getString("Files.urlIsntEndWith_ss"), //$NON-NLS-1$
                    removeSubPath, url));
        }
        int rlen = removeSubPath.length();
        // s=file:/C:/abc/dir/example.jar!/, or file:/C:/abc/dir/
        s = s.substring(0, s.length() - rlen);

        String protocol = url.getProtocol();
        if ("jar".equals(protocol)) { //$NON-NLS-1$
            // the jar URL is verified by above. so now can safely return.
            File jarFile = getJarFile(url);
            return new ZipResFolder(jarFile);
        }
        URL truncatedURL = new URL(s);
        File dir = canoniOf(truncatedURL);
        return new FileResFolder(dir);
    }

    private static File TMPDIR;

    static {
        File t;
        String TEMP;
        if ((TEMP = System.getenv("TEMP")) != null) //$NON-NLS-1$
            t = Files.canoniOf(TEMP);
        else if ((TEMP = System.getenv("TMP")) != null) //$NON-NLS-1$
            t = Files.canoniOf(TEMP);
        else
            t = Files.canoniOf("/tmp"); //$NON-NLS-1$
        if (t.exists()) {
            if (!t.isDirectory())
                throw new RuntimeException(SysNLS.getString("Files.notDir") + t); //$NON-NLS-1$
        } else
            t.mkdirs();
        TMPDIR = t;
    }

    /**
     * @see File#createTempFile(String, String)
     * @see File#createTempFile(String, String, File)
     */
    public static File getTmpDir() {
        return TMPDIR;
    }

    static Map<Object, File> temps;
    static Pattern invalidFilenameChars;
    static {
        invalidFilenameChars = Pattern.compile("[^a-zA-Z0-9-_]"); //$NON-NLS-1$
    }

    public static File convertToFile(Object key, Object in) throws IOException {
        String name = String.valueOf(key);
        name = invalidFilenameChars.matcher(name).replaceAll("_"); //$NON-NLS-1$
        return convertToFile(key, in, "CTF-" + name, ".tmp", TMPDIR); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static File convertToFile(Object key, Object in, String prefix, String suffix, File tmpdir)
            throws IOException {
        if (temps == null)
            temps = new HashMap<Object, File>();
        if (key != null) {
            File file = temps.get(key);
            if (file != null)
                return file;
        }
        File tmpFile = File.createTempFile(prefix, suffix, tmpdir);
        try {
            FileOutputStream out = new FileOutputStream(tmpFile);
            for (byte[] block : readByBlock(in)) {
                out.write(block);
            }
            out.close();
            tmpFile.deleteOnExit();
            if (key != null)
                temps.put(key, tmpFile);
            return tmpFile;
        } catch (IOException e) {
            tmpFile.delete();
            throw e;
        }
    }

    public static String getRelativeName(File file, File start) {
        if (start == null)
            throw new NullPointerException("start"); //$NON-NLS-1$
        file = Files.canoniOf(file);
        start = Files.canoniOf(start);
        List<String> tails = new ArrayList<String>();
        for (File look = file;; look = look.getParentFile()) {
            if (look == null)
                throw new UnexpectedException(String.format(SysNLS.getString("Files.fileNotInStart_ss"), file, start)); //$NON-NLS-1$
            if (look.equals(start))
                break;
            tails.add(look.getName());
        }
        StringBuffer buffer = null;
        for (int i = tails.size() - 1; i >= 0; i--) {
            if (buffer == null)
                buffer = new StringBuffer(tails.size() * 16);
            else
                buffer.append(slash);
            buffer.append(tails.get(i));
        }
        if (buffer == null)
            return ""; //$NON-NLS-1$
        return buffer.toString();
    }

    public static File getAbsoluteFile(File start, String relativeName) {
        if (relativeName == null || relativeName.isEmpty())
            return start;
        return Files.canoniOf(start, relativeName);
    }

    /** get name without extension */
    public static String getName(File file) {
        String name = file.getName();
        int dot = name.lastIndexOf('.');
        if (dot != -1)
            return name.substring(0, dot);
        return name;
    }

    public static String getName(String file) {
        return getName(new File(file));
    }

    /**
     * @return "" if file has no extension.
     */
    public static String getExtension(String file, boolean includeDot) {
        int dot = file.lastIndexOf('.');
        if (dot != -1)
            return file.substring(includeDot ? dot : dot + 1);
        return ""; //$NON-NLS-1$
    }

    /**
     * @return without dot, "" if file has no extension.
     */
    public static String getExtension(String file) {
        return getExtension(file, false);
    }

    /**
     * @return "" if file has no extension.
     */
    public static String getExtension(File file, boolean includeDot) {
        return getExtension(file.getName(), includeDot);
    }

    /**
     * @return without dot, "" if file has no extension.
     */
    public static String getExtension(File file) {
        return getExtension(file.getName(), false);
    }

    /**
     * @see CharFeature Part.1, is-text
     */
    static Bits textBits;
    static {
        textBits = new Bits.IntvLE( //
                134231808, -1, -1, -1, -1, -1, -1, -1);
    }

    static int textLookSize = blockSize;
    static int textFailSize = (int) 0.90f * textLookSize;

    public static boolean isText(byte[] bytes) {
        int lookSize = Math.min(textLookSize, bytes.length);
        int fail = 0;
        for (int i = 0; i < lookSize; i++) {
            int b = IntMath.unsign(bytes[i]);
            if (!textBits.test(b))
                if (++fail > textFailSize)
                    return false;
        }
        return true;
    }

    /**
     * Treat inaccessible file as binary treat inaccessible file as binary
     * 
     * @return <code>true</code> if file is text like.
     */
    public static boolean isText(File file) {
        byte[] block;
        try {
            block = readBytes(file, textLookSize);
        } catch (IOException e) {
            // throw new RuntimeException(e);
            return false;
        }
        return isText(block);
    }

    public static boolean isBinary(byte[] bytes) {
        return !isText(bytes);
    }

    public static boolean isBinary(File file) {
        return !isText(file);
    }

    public static File findBase(File... files) {
        if (files.length == 0)
            return null;
        File base = files[0];
        for (int i = 1; i < files.length; i++) {
            base = findBase(base, files[i]);
            if (base == null)
                return null;
        }
        return base;
    }

    public static File findBase(File a, File b) {
        if (a == null || b == null)
            return null;
        if (a.equals(b))
            return a;
        final File _a = a.getParentFile();
        for (File i = a; i != null; i = i.getParentFile())
            if (i.equals(b))
                return b;
        final File _b = b.getParentFile();
        for (File i = b; i != null; i = i.getParentFile())
            if (i.equals(a))
                return a;
        return findBase(_a, _b);
    }

    // File system operations

    /**
     * @return <code>true</code> if succeeded.
     */
    public static boolean touch(File file, long time) {
        return file.setLastModified(time);
    }

    public static boolean touch(File file) {
        return touch(file, System.currentTimeMillis());
    }

    public static boolean deleteTree(File start) {
        assert start != null;
        if (!start.exists())
            return false;
        if (start.isFile())
            return start.delete();
        assert start.isDirectory();
        File[] children = start.listFiles();
        boolean succ = true;
        for (File child : children) {
            succ = deleteTree(child) && succ;
        }
        succ = start.delete() && succ;
        return succ;
    }

    public static boolean move(File src, File dst, boolean force) throws IOException {
        if (dst.exists())
            if (force) {
                if (dst.delete())
                    return move(src, dst, false);
                throw new IOException(SysNLS.getString("Files.cantDelete") + dst); //$NON-NLS-1$
            } else
                throw new IOException(String.format(SysNLS.getString("Files.destExisted_s"), dst)); //$NON-NLS-1$
        if (src.renameTo(dst))
            return true;
        if (copy(src, dst))
            return src.delete();
        throw new IOException(String.format(SysNLS.getString("Files.cantCopy_ss"), src, dst)); //$NON-NLS-1$
    }

    public static boolean move(File src, File dst) throws IOException {
        return move(src, dst, false);
    }

    public static boolean copy(Object src, Object dst, boolean append) throws IOException {
        assert src != null : "null src"; //$NON-NLS-1$
        assert dst != null : "null dst"; //$NON-NLS-1$
        boolean closeIn = shouldClose(src);
        boolean closeOut = shouldClose(dst);
        InputStream in = getInputStream(src);
        OutputStream out = null;
        try {
            out = getOutputStream(dst, append);
            byte[] buf = new byte[blockSize];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } finally {
            if (closeIn)
                in.close();
            if (closeOut && out != null)
                out.close();
        }
        return true;
    }

    public static boolean copy(Object src, Object dst) throws IOException {
        return copy(src, dst, false);
    }

    /**
     * @return first position of difference, or -1 if the two are same.
     */
    public static long diff_1(Object src, Object dst) throws IOException {
        if (src == dst)
            return -1;
        if (src == null || dst == null)
            return 0;
        if (src instanceof String)
            src = new File((String) src);
        if (dst instanceof String)
            dst = new File((String) dst);
        File srcf = null;
        File dstf = null;
        if (src instanceof File)
            if (!(srcf = ((File) src)).canRead())
                return 0;
        if (dst instanceof File)
            if (!(dstf = ((File) dst)).canRead())
                return 0;
        if (srcf != null && dstf != null) {
            if (srcf.length() != dstf.length())
                return 0;
        }
        boolean closeSrc = shouldClose(src);
        boolean closeDst = shouldClose(dst);
        InputStream s = getInputStream(src);
        InputStream d = null;
        try {
            d = getInputStream(dst);
            byte[] sbuf = new byte[blockSize];
            byte[] dbuf = new byte[blockSize];
            long offset = 0;
            int slen;
            while ((slen = s.read(sbuf)) != -1) {
                int dlen = d.read(dbuf, 0, slen);
                if (dlen == -1)
                    return offset;
                while (dlen != slen) {
                    int dmore = d.read(dbuf, dlen, slen - dlen);
                    if (dmore == -1)
                        return offset + dlen;
                    dlen += dmore;
                }
                assert slen == dlen;
                if (slen == sbuf.length)
                    if (Arrays.equals(sbuf, dbuf)) {
                        offset += slen;
                        continue;
                    }
                for (int i = 0; i < slen; i++)
                    if (sbuf[i] != dbuf[i])
                        return offset + i;
                offset += slen;
            }
        } finally {
            if (closeSrc)
                s.close();
            if (closeDst && d != null)
                d.close();
        }
        return -1;
    }

    public static boolean equals(Object src, Object dst) throws IOException {
        return diff_1(src, dst) == -1;
    }

    /**
     * if difference info is available, then return the first difference. otherwise return
     * <code>false</code> if any different exists.
     * 
     * @return <code>null</code> if the same
     */
    public static Object copyDiff(Object src, Object dst, DiffComparator diff) throws IOException {
        Object ret;
        if (diff != null) {
            List<String> al = Files.readLines(src);
            List<String> bl = Files.readLines(dst);
            List<DiffInfo> diffs = diff.diffCompare(al, bl);
            if (diffs.size() == 0)
                return null;
            ret = diffs;
        } else {
            if (equals(src, dst))
                return null;
            ret = false;
        }
        write(dst, src);
        return ret;
    }

    /**
     * return <code>true</code> if the two are diff and actually copied.
     */
    public static boolean copyDiff(Object src, Object dst) throws IOException {
        return copyDiff(src, dst, null) != null;
    }

    /**
     * @param inputs
     *            each input may be:
     *            <ul>
     *            <li>File inputFile
     *            <li>String inputFileName
     *            <li>Predicate2(output, File[] inputs) rule
     *            </ul>
     */
    @SuppressWarnings("unchecked")
    public static boolean make(File output, Object... inputs) {
        assert output != null;
        output = Files.canoniOf(output);
        File outd = output.getParentFile();
        List<File> files = new ArrayList<File>();
        long outl = output.exists() ? output.lastModified() : 0l;
        long mostRecent = outl;
        boolean succeeded = true;
        for (Object input : inputs) {
            assert input != null;
            File inputf;
            if (input instanceof File)
                inputf = (File) input;
            else if (input instanceof String)
                inputf = Files.canoniOf(outd, (String) input);
            else if (input instanceof Pred2) {
                if (mostRecent > outl) {
                    File[] finputs = files.toArray(Empty.Files);
                    Pred2<File, File[]> ruledef = (Pred2<File, File[]>) input;
                    boolean succ = ruledef.eval(output, finputs);
                    succeeded = succeeded && succ;
                }
                files.clear();
                continue;
            } else
                throw new IllegalArgumentTypeException(input);
            if (!inputf.exists())
                throw new IllegalArgumentException(SysNLS.getString("Files.inputNotExist") //$NON-NLS-1$
                        + inputf);
            files.add(inputf);
            if (inputf.lastModified() > mostRecent)
                mostRecent = inputf.lastModified();
        }
        return succeeded;
    }

    /**
     * @return <code>null</code> if no match.
     */
    public static List<File> find(String wildPath) {
        List<File> found = new ArrayList<File>();
        find(wildPath, found);
        if (found.size() == 0)
            return null;
        return found;
    }

    static void find(String wildPath, Collection<File> found) {
        int ast = wildPath.indexOf('*');
        if (ast == -1) {
            File f = new File(wildPath);
            if (f.exists())
                found.add(f);
        } else {
            int estart = wildPath.lastIndexOf('/', ast);
            if (estart == -1)
                throw new IllegalArgumentException(SysNLS.getString("Files.invalidWildExp") //$NON-NLS-1$
                        + wildPath);
            estart++;
            int eend = wildPath.indexOf('/', ast);
            if (eend == -1)
                eend = wildPath.length();
            String elm = wildPath.substring(estart, eend);
            String regex = elm;
            regex = regex.replace(".", "\\."); //$NON-NLS-1$ //$NON-NLS-2$
            regex = regex.replace("*", ".*"); //$NON-NLS-1$ //$NON-NLS-2$
            Pattern p = Pattern.compile("^" + regex + "$"); //$NON-NLS-1$ //$NON-NLS-2$
            File parent = new File(wildPath.substring(0, estart));
            if (!parent.isDirectory()) {
                // maybe file or non-exist.
                // usually occured when multiple-* scan.
                return;
            }
            for (File f : parent.listFiles()) {
                String fname = f.getName();
                if (p.matcher(fname).matches()) {
                    String it = f.getPath() + wildPath.substring(eend);
                    find(it, found);
                }
            }
        }
    }

    static File[] sysPaths;
    static String[] sysExts;
    static {
        String ps = System.getProperty("path.separator");
        if (ps == null)
            ps = ":";
        String pathenv = System.getenv("PATH");
        if (pathenv == null)
            sysPaths = new File[0];
        else {
            String[] v = pathenv.split(ps);
            sysPaths = new File[v.length];
            for (int i = 0; i < v.length; i++)
                sysPaths[i] = new File(v[i]);
        }

        String pathextenv = System.getenv("PATHEXT");
        if (pathextenv == null)
            sysExts = new String[0];
        else {
            String[] v = pathextenv.split(ps);
            // sysExts = new String[v.length];
            // for (int i = 0; i < v.length; i++)
            // sysExts[i] = "." + v[i];
            sysExts = v;
        }
    }

    /**
     * Find program using system default PATHEXT (win32 only).
     * 
     * @return <code>null</code> if couldn't find name.
     */
    public static File which(String name, File... paths) {
        return which(name, sysExts, paths);
    }

    /**
     * @return <code>null</code> if couldn't find name.
     */
    public static File which(String name, String[] pathExts, File... paths) {
        if (paths == null || paths.length == 0)
            paths = sysPaths;
        for (File path : paths) {
            File f = new File(path, name);
            if (f.isFile())
                return f;
            if (pathExts != null)
                for (String ext : pathExts) {
                    f = new File(path, name + ext);
                    if (f.isFile())
                        return f;
                }
        }
        return null;
    }

}
