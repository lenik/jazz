package net.bodz.bas.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import net.bodz.bas.lang.Predicate2;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.PrefetchedIterator;

public class Files {

    public static Charset encoding  = Charset.defaultCharset();
    public static int     blockSize = 4096;
    private static String slash;
    static {
        slash = System.getProperty("file.separator");
        if (slash == null)
            slash = "/";
    }

    // adapters

    public static Charset getCharset(Object charset) {
        if (charset == null)
            return encoding;
        if (charset instanceof Charset)
            return (Charset) charset;
        if (charset instanceof String)
            return Charset.forName((String) charset);
        throw new IllegalArgumentException("illegal charset type: " + charset);
    }

    public static CharsetEncoder getCharsetEncoder(Object charset) {
        if (charset == null)
            return encoding.newEncoder();
        if (charset instanceof CharsetEncoder)
            return (CharsetEncoder) charset;
        if (charset instanceof Charset)
            return ((Charset) charset).newEncoder();
        if (charset instanceof String)
            return Charset.forName((String) charset).newEncoder();
        throw new IllegalArgumentException("illegal charset type: " + charset);
    }

    public static CharsetDecoder getCharsetDecoder(Object charset) {
        if (charset == null)
            return encoding.newDecoder();
        if (charset instanceof CharsetDecoder)
            return (CharsetDecoder) charset;
        if (charset instanceof Charset)
            return ((Charset) charset).newDecoder();
        if (charset instanceof String)
            return Charset.forName((String) charset).newDecoder();
        throw new IllegalArgumentException("illegal charset type: " + charset);
    }

    public static InputStream getInputStream(Object in, Object charset)
            throws IOException {
        if (in == null)
            return null;
        if (in instanceof InputStream)
            return (InputStream) in;
        if (in instanceof Reader)
            return new ReaderInputStream((Reader) in, getCharset(charset));
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
        throw new IllegalArgumentException(
                "Can't convert type to InputStream: " + in.getClass());
    }

    public static InputStream getInputStream(Object in) throws IOException {
        return getInputStream(in, encoding);
    }

    public static OutputStream getOutputStream(Object out, Object charset,
            boolean append) throws IOException {
        if (out == null)
            return null;
        if (out instanceof OutputStream)
            return (OutputStream) out;
        if (out instanceof Writer)
            return new WriterOutputStream((Writer) out, getCharset(charset));
        if (out instanceof File)
            return new FileOutputStream((File) out, append);
        if (out instanceof String)
            return new FileOutputStream((String) out, append);
        if (out instanceof URL)
            try {
                return new FileOutputStream(new File(((URL) out).toURI()),
                        append);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        if (out instanceof URI)
            return new FileOutputStream(new File((URI) out), append);
        throw new IllegalArgumentException(
                "Can't convert type to InputStream: " + out.getClass());
    }

    public static OutputStream getOutputStream(Object out, boolean append)
            throws IOException {
        return getOutputStream(out, encoding, append);
    }

    public static OutputStream getOutputStream(Object out, Object charset)
            throws IOException {
        return getOutputStream(out, charset, false);
    }

    public static OutputStream getOutputStream(Object out) throws IOException {
        return getOutputStream(out, encoding);
    }

    public static Reader getReader(Object in, Object charset)
            throws IOException {
        if (in == null)
            return null;
        if (in instanceof Reader)
            return (Reader) in;
        if (in instanceof char[])
            return new StringReader(new String((char[]) in));
        InputStream ins = getInputStream(in);
        return new InputStreamReader(ins, getCharset(charset));
    }

    public static Reader getReader(Object in) throws IOException {
        return getReader(in, encoding);
    }

    public static BufferedReader getBufferedReader(Object in, Object charset)
            throws IOException {
        if (in == null)
            return null;
        if (in instanceof BufferedReader)
            return (BufferedReader) in;
        return new BufferedReader(getReader(in, charset));
    }

    public static BufferedReader getBufferedReader(Object in)
            throws IOException {
        return getBufferedReader(in, encoding);
    }

    public static Writer getWriter(Object out, Object charset, boolean append)
            throws IOException {
        if (out == null)
            return null;
        if (out instanceof Writer)
            return (Writer) out;
        OutputStream outs = getOutputStream(out, append);
        return new OutputStreamWriter(outs, getCharset(charset));
    }

    public static Writer getWriter(Object out, boolean append)
            throws IOException {
        return getWriter(out, encoding, append);
    }

    public static Writer getWriter(Object out, Object charset)
            throws IOException {
        return getWriter(out, charset, false);
    }

    public static Writer getWriter(Object out) throws IOException {
        return getWriter(out, encoding, false);
    }

    public static boolean shouldClose(Object io) {
        if (io instanceof File)
            return true;
        if (io instanceof String)
            return true;
        return false;
    }

    public static byte[] readBytes(Object in, long size, Object charset,
            boolean close) throws IOException {
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

    public static byte[] readBytes(Object in, long size, Object charset)
            throws IOException {
        boolean close = shouldClose(in);
        return readBytes(in, size, close);
    }

    public static byte[] readBytes(Object in, long size, boolean close)
            throws IOException {
        return readBytes(in, size, encoding, close);
    }

    public static byte[] readBytes(Object in, long size) throws IOException {
        return readBytes(in, size, encoding);
    }

    public static byte[] readBytes(Object in, Object charset, boolean close)
            throws IOException {
        return readBytes(in, -1, charset, close);
    }

    public static byte[] readBytes(Object in, Object charset)
            throws IOException {
        boolean close = shouldClose(in);
        return readBytes(in, close);
    }

    public static byte[] readBytes(Object in, boolean close) throws IOException {
        return readBytes(in, encoding, close);
    }

    public static byte[] readBytes(Object in) throws IOException {
        return readBytes(in, encoding);
    }

    public static String readAll(Object in, Object charset, boolean close)
            throws IOException {
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
        return readAll(in, encoding, close);
    }

    public static String readAll(Object in) throws IOException {
        return readAll(in, encoding);
    }

    public static List<String> readLines(Object in, Object charset,
            boolean close) throws IOException {
        BufferedReader reader = getBufferedReader(in, charset);
        List<String> lines = new ArrayList<String>();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (close)
                reader.close();
        }
        return lines;
    }

    public static List<String> readLines(Object in, Object charset)
            throws IOException {
        boolean close = shouldClose(in);
        return readLines(in, charset, close);
    }

    public static List<String> readLines(Object in, boolean close)
            throws IOException {
        return readLines(in, encoding, close);
    }

    public static List<String> readLines(Object in) throws IOException {
        return readLines(in, encoding);
    }

    public static String readLine(Object in, Object charset, boolean close,
            int index, Pattern pattern) throws IOException {
        assert index > 0;
        BufferedReader reader = getBufferedReader(in, charset);
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

    public static String readLine(Object in, Object charset, boolean close)
            throws IOException {
        return readLine(in, charset, close, 1, null);
    }

    public static String readLine(Object in, Object charset) throws IOException {
        boolean close = shouldClose(in);
        return readLine(in, charset, close);
    }

    public static String readLine(Object in, boolean close) throws IOException {
        return readLine(in, encoding, close);
    }

    public static String readLine(Object in) throws IOException {
        return readLine(in, encoding);
    }

    public static String readTill(Object in, char term, Charset charset,
            boolean close) throws IOException {
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

    public static String readTill(Object in, char term, Charset charset)
            throws IOException {
        boolean close = shouldClose(in);
        return readTill(in, term, charset, close);
    }

    public static String readTill(Object in, char term, boolean close)
            throws IOException {
        return readTill(in, term, encoding, close);
    }

    public static String readTill(Object in, char term) throws IOException {
        return readTill(in, term, encoding);
    }

    public static String readLen(Object in, int length, Charset charset,
            boolean close) throws IOException {
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

    public static String readLen(Object in, int length, Charset charset)
            throws IOException {
        boolean close = shouldClose(in);
        return readLen(in, length, charset, close);
    }

    public static String readLen(Object in, int length, boolean close)
            throws IOException {
        return readLen(in, length, encoding, close);
    }

    public static String readLen(Object in, int length) throws IOException {
        return readLen(in, length, encoding);
    }

    protected static Iterator<Integer> _readByBlock(final Object[] files,
            final byte[] buffer) {
        assert files != null : "null files[]";
        assert buffer != null : "null buffer";

        return new PrefetchedIterator<Integer>() {

            private int         index = -1;
            private InputStream input = null;
            private boolean     close;

            @Override
            public Object fetch() {
                if (index >= files.length)
                    return END;
                if (input == null)
                    nextFile();
                int len;
                try {
                    len = input.read(buffer);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
                if (len == -1) {
                    nextFile();
                    return fetch();
                }
                return len;
            }

            protected boolean nextFile() {
                if (input != null && close)
                    try {
                        input.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                if (++index >= files.length)
                    return false;
                try {
                    input = getInputStream(files[index]);
                    close = shouldClose(files[index]);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
                return true;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    public static Iterable<Integer> readByBlock(final byte[] buffer,
            final Object... files) {
        return new Iterable<Integer>() {

            @Override
            public Iterator<Integer> iterator() {
                return _readByBlock(files, buffer);
            }

        };
    }

    public static Iterable<byte[]> readByBlock(final int blockSize,
            final Object... files) {
        final byte[] buffer = new byte[blockSize];
        final Iterator<Integer> it = _readByBlock(files, buffer);
        return new Iterable<byte[]>() {

            @Override
            public Iterator<byte[]> iterator() {
                return new Iterator<byte[]>() {

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

    public static Iterable<byte[]> readByBlock(final Object... files) {
        return readByBlock(blockSize, files);
    }

    protected static Iterator<String> _readByLine(final Object[] files,
            final Charset charset) {
        return new PrefetchedIterator<String>() {

            private int            fileIndex = 0;
            private BufferedReader reader    = null;

            @Override
            public Object fetch() {
                if (fileIndex >= files.length)
                    return END;
                if (reader == null) {
                    Object in = files[fileIndex];
                    assert in != null : "null file";
                    try {
                        in = getBufferedReader(in, charset);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                    reader = (BufferedReader) in;
                }
                String line;
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
                if (line == null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                    fileIndex++;
                    return fetch();
                }
                return line;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    public static Iterable<String> readByLine(final Object... files) {
        return new Iterable<String>() {

            @Override
            public Iterator<String> iterator() {
                return _readByLine(files, encoding);
            }

        };
    }

    public static Iterable<String> readByLine2(final String charset,
            final Object... files) {
        return new Iterable<String>() {

            @Override
            public Iterator<String> iterator() {
                return _readByLine(files, getCharset(charset));
            }

        };
    }

    // loadProperties

    public static Properties loadProperties(Object in, Object charset,
            boolean close) throws IOException {
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

    public static Properties loadProperties(Object in, Object charset)
            throws IOException {
        boolean close = shouldClose(in);
        return loadProperties(in, charset, close);
    }

    public static Properties loadProperties(Object in, boolean close)
            throws IOException {
        return loadProperties(in, encoding, close);
    }

    public static Properties loadProperties(Object in) throws IOException {
        return loadProperties(in, encoding);
    }

    // writeTo

    public static PrintStream writeTo(Object out, Object charset)
            throws IOException {
        boolean autoFlush = false;
        String cs = getCharset(charset).name();
        return new PrintStream(getOutputStream(out), autoFlush, cs);
    }

    public static PrintStream writeTo(Object out) throws IOException {
        return writeTo(out, encoding);
    }

    // write

    public static <T> void write(Object out, T data, Object charset,
            boolean append) throws IOException {
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

    public static <T> void write(Object out, T data, Object charset)
            throws IOException {
        write(out, data, charset, false);
    }

    public static <T> void write(Object out, T data) throws IOException {
        write(out, data, encoding);
    }

    public static <T> void write(Object out, byte[] data, int off, int len)
            throws IOException {
        OutputStream outs = getOutputStream(out);
        boolean close = shouldClose(out);
        outs.write(data, off, len);
        if (close)
            outs.close();
    }

    public static PrintStream appendTo(Object out, Object charset)
            throws IOException {
        if (out instanceof File)
            return appendTo(new FileOutputStream((File) out, true), charset);
        if (out instanceof String)
            return appendTo(new FileOutputStream((String) out, true), charset);
        OutputStream outs = getOutputStream(out);
        boolean autoFlush = true;
        String cs = getCharset(charset).name();
        return new PrintStream(outs, autoFlush, cs);
    }

    public static PrintStream appendTo(Object out) throws IOException {
        return appendTo(out, encoding);
    }

    public static <T> void append(Object out, T data, Object charset)
            throws IOException {
        write(out, data, charset, true);
    }

    public static <T> void append(Object out, T data) throws IOException {
        write(out, data, encoding, true);
    }

    public static <T> void append(Object out, byte[] data, int off, int len)
            throws IOException {
        OutputStream outs = getOutputStream(out, true);
        boolean close = shouldClose(out);
        outs.write(data, off, len);
        if (close)
            outs.close();
    }

    // _load

    private static Object _NA = new Object();

    public static Iterable<Object> _load(Object in) throws IOException {
        final ObjectInput _objin = (in instanceof ObjectInput) ? (ObjectInput) in
                : new ObjectInputStream(getInputStream(in));
        final boolean close = shouldClose(in);

        final Iterator<Object> iterator = new Iterator<Object>() {
            private ObjectInput objin = _objin;
            private Object      next  = _NA;

            @Override
            public boolean hasNext() {
                if (next == _NA)
                    next = next();
                if (next == _NA)
                    if (objin != null && close)
                        try {
                            objin.close();
                            objin = null;
                        } catch (IOException e) {
                            throw new RuntimeException(e.getMessage(), e);
                        }
                return next != _NA;
            }

            @Override
            public Object next() {
                if (next != _NA) {
                    Object t = next;
                    next = _NA;
                    return t;
                }
                Object t;
                try {
                    t = objin.readObject();
                } catch (ClassNotFoundException e) {
                    throw new IdentifiedException(e.getMessage(), e);
                } catch (IOException e) {
                    throw new IdentifiedException(e.getMessage(), e);
                }
                return t;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return new Iterable<Object>() {
            @Override
            public Iterator<Object> iterator() {
                return iterator;
            }
        };
    }

    public static Object[] loadAll(Object in) throws IOException {
        List<Object> buf = new ArrayList<Object>();
        for (Object o : _load(in)) {
            buf.add(o);
        }
        return buf.toArray();
    }

    public static Object load(Object in, int index) throws IOException {
        assert index >= 0 : "Invalid index " + index;
        for (Object o : _load(in)) {
            if (index-- == 0)
                return o;
        }
        throw new IndexOutOfBoundsException("scan over the end of in: " + index);
    }

    public static Object load(Object in) throws IOException {
        return load(in, 0);
    }

    // data associated with a class

    public static URL classData(Class<?> clazz, String extension) {
        ClassLoader loader = clazz.getClassLoader();
        String className = clazz.getName();
        String fileName = className.replace('.', '/') + "." + extension;
        return loader.getResource(fileName);
    }

    /**
     * Same as {@link #classData(Class, String)} with ".class" as
     * <code>extension</code>.
     */
    public static URL classData(Class<?> clazz) {
        return classData(clazz, ".class");
    }

    public static URL getDataURL(Object object, String name) {
        return classData(object.getClass(), name);
    }

    public static URL getRootResource(Class<?> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        if (classLoader == null)
            throw new NullPointerException("can't getClassLoader");
        String pkg = clazz.getPackage().getName();
        String base = clazz.getSimpleName() + ".class";
        String name = pkg.replace('.', '/') + '/' + base;
        return getRootResource(classLoader, name);
    }

    public static URL getRootResource(ClassLoader classLoader, String hintPath) {
        hintPath = hintPath.replace('\\', '/');
        URL url = classLoader.getResource(hintPath);
        String s = url.toString();
        if (!s.endsWith(hintPath))
            throw new UnexpectedException(
                    "getResource returned an unknown URL format");
        s = s.substring(0, s.length() - hintPath.length());
        try {
            url = new URL(s);
        } catch (MalformedURLException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
        if (url == null)
            throw new Error("failed to get root resource");
        return url;
    }

    // dump

    public static void dump(Object out, Object... objs) throws IOException {
        assert out != null;
        ObjectOutput objout = (out instanceof ObjectOutput) ? (ObjectOutput) out
                : new ObjectOutputStream(getOutputStream(out));
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

    public static File canoniOf(String path) throws NullPointerException {
        return canoniOf(new File(path));
    }

    public static File canoniOf(URI uri) throws NullPointerException {
        return canoniOf(new File(uri));
    }

    public static File canoniOf(URL url) throws NullPointerException {
        try {
            return canoniOf(new File(url.toURI()));
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
        throw new IllegalArgumentException();
    }

    public static File canoniOf(Object parent, String child) {
        File _parent = canoniOf(parent);
        return canoniOf(new File(_parent, child));
    }

    private static File TMPDIR;

    public static synchronized File getTmpDir() {
        if (TMPDIR == null) {
            File t;
            String TEMP;
            if ((TEMP = System.getenv("TEMP")) != null)
                t = Files.canoniOf(TEMP);
            else if ((TEMP = System.getenv("TMP")) != null)
                t = Files.canoniOf(TEMP);
            else
                t = Files.canoniOf("/tmp");
            if (t.exists()) {
                if (!t.isDirectory())
                    throw new RuntimeException("not a directory: " + t);
            } else
                t.mkdirs();
            TMPDIR = t;
        }
        return TMPDIR;
    }

    public static String getRelativeName(File file, File start) {
        if (start == null)
            throw new NullPointerException("start");
        file = Files.canoniOf(file);
        start = Files.canoniOf(start);
        List<String> tails = new ArrayList<String>();
        for (File look = file;; look = look.getParentFile()) {
            if (look == null)
                throw new UnexpectedException("file " + file
                        + " not in the start dir " + start);
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
            return "";
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

    public static String getExtension(String file, boolean includeDot) {
        int dot = file.lastIndexOf('.');
        if (dot != -1)
            return file.substring(includeDot ? dot : dot + 1);
        return "";
    }

    public static String getExtension(String file) {
        return getExtension(file, false);
    }

    public static String getExtension(File file, boolean includeDot) {
        return getExtension(file.getName(), includeDot);
    }

    public static String getExtension(File file) {
        return getExtension(file.getName(), false);
    }

    public static boolean isText(File file) {
        return false;
    }

    public static boolean isBinary(File file) {
        return !isText(file);
    }

    // File system operations

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

    public static boolean move(File src, File dst, boolean force)
            throws IOException {
        if (dst.exists())
            if (force) {
                if (dst.delete())
                    return move(src, dst, false);
                throw new IOException("Can't delete the existed file " + dst);
            } else
                throw new IOException("Destination " + dst
                        + " has been existed");
        if (src.renameTo(dst))
            return true;
        if (copy(src, dst))
            return src.delete();
        throw new IOException("Failed to copy " + src + " to " + dst);
    }

    public static boolean move(File src, File dst) throws IOException {
        return move(src, dst, false);
    }

    public static boolean copy(Object src, Object dst, boolean append)
            throws IOException {
        assert src != null : "null src";
        assert dst != null : "null dst";
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

    public static Object copyDiff(Object src, Object dst, DiffComparator diff)
            throws IOException {
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
                return false;
            ret = true;
        }
        write(dst, src);
        return ret;
    }

    public static boolean copyDiff(Object src, Object dst) throws IOException {
        return (Boolean) copyDiff(src, dst, null);
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
            else if (input instanceof Predicate2) {
                if (mostRecent > outl) {
                    File[] finputs = files.toArray(Empty.Files);
                    Predicate2<File, File[]> ruledef = (Predicate2<File, File[]>) input;
                    boolean succ = ruledef.eval(output, finputs);
                    succeeded = succeeded && succ;
                }
                files.clear();
                continue;
            } else
                throw new IllegalArgumentException("input type not accepted: "
                        + input.getClass());
            if (!inputf.exists())
                throw new IllegalArgumentException("input file isn't existed: "
                        + inputf);
            files.add(inputf);
            if (inputf.lastModified() > mostRecent)
                mostRecent = inputf.lastModified();
        }
        return succeeded;
    }

}
