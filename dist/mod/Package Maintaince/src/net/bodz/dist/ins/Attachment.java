package net.bodz.dist.ins;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.dist.nls.PackNLS;

public class Attachment implements IAttachment {

    final URL    url;
    final File   file;
    final String encoding;

    StateBase    stated;

    public Attachment(ResourceUnion res) {
        this(res.getURL(), res.getFile());
    }

    public Attachment(ResourceUnion res, String encoding) {
        this(res.getURL(), res.getFile(), encoding);
    }

    public Attachment(URL url, File file) {
        this(url, file, null);
    }

    public Attachment(URL url, File file, String encoding) {
        this.url = url;
        this.file = file;
        this.encoding = encoding;
    }

    @Override
    public Reader getReader() throws IOException {
        if (stated == null)
            stated = new UsingReader(url, encoding);
        return stated.getReader();
    }

    @Override
    public Writer getWriter() throws IOException {
        if (stated == null)
            stated = new UsingWriter(file, encoding);
        return stated.getWriter();
    }

    @Override
    public InputStream getIn() throws IOException {
        if (stated == null)
            stated = new UsingInputStream(url);
        return stated.getIn();
    }

    @Override
    public PrintStream getOut() throws IOException {
        if (stated == null)
            stated = new UsingPrintStream(file, encoding);
        return stated.getOut();
    }

    @Override
    public OutputStream _getOut() throws IOException {
        if (stated == null)
            stated = new UsingOutputStream(file);
        return stated._getOut();
    }

    @Override
    public ObjectInputStream getObjectIn() throws IOException {
        if (stated == null)
            stated = new UsingObjectInputStream(url);
        return stated.getObjectIn();
    }

    @Override
    public ObjectOutputStream getObjectOut() throws IOException {
        if (stated == null)
            stated = new UsingObjectOutputStream(file);
        return stated.getObjectOut();
    }

    @Override
    public ZipInputStream getZipIn() throws IOException {
        if (stated == null)
            stated = new UsingZipInputStream(url);
        return stated.getZipIn();
    }

    @Override
    public ZipOutputStream getZipOut() throws IOException {
        if (stated == null)
            stated = new UsingZipOutputStream(file);
        return stated.getZipOut();
    }

    @Override
    public JarInputStream getJarIn() throws IOException {
        if (stated == null)
            stated = new UsingJarInputStream(url);
        return stated.getJarIn();
    }

    @Override
    public JarOutputStream getJarOut() throws IOException {
        if (stated == null)
            stated = new UsingJarOutputStream(file);
        return stated.getJarOut();
    }

    @Override
    public Object load() throws IOException {
        throw new NotImplementedException("don't know how to load."); //$NON-NLS-1$
    }

    @Override
    public void close() throws IOException {
        if (stated != null) {
            stated.close();
            stated = null;
        }
    }

    @Override
    public String toString() {
        String s = PackNLS.getString("Attachment.attachment"); //$NON-NLS-1$
        if (url != null && file == null)
            s += url;
        else
            s += file;
        return s;
    }

    public abstract static class StateBase implements IAttachment {

        protected String error;

        public StateBase(String accessType) {
            this.error = PackNLS.getString("Attachment.currentlyAccess") + accessType; //$NON-NLS-1$
        }

        @Override
        public Reader getReader() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public Writer getWriter() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public InputStream getIn() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public PrintStream getOut() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public OutputStream _getOut() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ObjectInputStream getObjectIn() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ObjectOutputStream getObjectOut() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ZipInputStream getZipIn() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ZipOutputStream getZipOut() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public JarInputStream getJarIn() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public JarOutputStream getJarOut() throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public Object load() throws IOException {
            throw new IllegalStateException(error);
        }
    }

    static class UsingInputStream extends StateBase {

        final URL   url;
        InputStream in;

        public UsingInputStream(URL url) {
            super("InputStream"); //$NON-NLS-1$
            this.url = url;
        }

        @Override
        public InputStream getIn() throws IOException {
            if (in == null)
                in = url.openStream();
            return in;
        }

        @Override
        public void close() throws IOException {
            if (in != null) {
                in.close();
                in = null;
            }
        }

    }

    static class UsingReader extends StateBase {

        final URL    url;
        final String encoding;
        Reader       reader;

        public UsingReader(URL url, String encoding) {
            super("Reader"); //$NON-NLS-1$
            this.url = url;
            this.encoding = encoding;
        }

        @Override
        public Reader getReader() throws IOException {
            if (reader == null) {
                InputStream in = url.openStream();
                if (encoding == null)
                    reader = new InputStreamReader(in);
                else
                    reader = new InputStreamReader(in, encoding);
            }
            return reader;
        }

        @Override
        public void close() throws IOException {
            if (reader != null) {
                reader.close();
                reader = null;
            }
        }

    }

    static class UsingObjectInputStream extends StateBase {

        final URL         url;
        ObjectInputStream objin;

        public UsingObjectInputStream(URL url) {
            super("ObjectInputStream"); //$NON-NLS-1$
            this.url = url;
        }

        @Override
        public ObjectInputStream getObjectIn() throws IOException {
            if (objin == null) {
                InputStream in = url.openStream();
                objin = new ObjectInputStream(in);
            }
            return objin;
        }

        @Override
        public void close() throws IOException {
            if (objin != null) {
                objin.close();
                objin = null;
            }
        }

    }

    static class UsingZipInputStream extends StateBase {

        final URL      url;
        ZipInputStream zin;

        public UsingZipInputStream(URL url) {
            super("ZipInputStream"); //$NON-NLS-1$
            this.url = url;
        }

        @Override
        public ZipInputStream getZipIn() throws IOException {
            if (zin == null) {
                InputStream in = url.openStream();
                zin = new ZipInputStream(in);
            }
            return zin;
        }

        @Override
        public void close() throws IOException {
            if (zin != null) {
                zin.close();
                zin = null;
            }
        }

    }

    static class UsingJarInputStream extends StateBase {

        final URL      url;
        JarInputStream jin;

        public UsingJarInputStream(URL url) {
            super("JarInputStream"); //$NON-NLS-1$
            this.url = url;
        }

        @Override
        public JarInputStream getJarIn() throws IOException {
            if (jin == null) {
                InputStream in = url.openStream();
                jin = new JarInputStream(in);
            }
            return jin;
        }

        @Override
        public void close() throws IOException {
            if (jin != null) {
                jin.close();
                jin = null;
            }
        }

    }

    static class UsingOutputStream extends StateBase {

        final File   file;
        OutputStream out;

        public UsingOutputStream(File file) {
            super("OutputStream"); //$NON-NLS-1$
            this.file = file;
        }

        @Override
        public OutputStream _getOut() throws IOException {
            if (out == null) {
                out = new FileOutputStream(file);
            }
            return out;
        }

        @Override
        public void close() throws IOException {
            if (out != null) {
                out.close();
                out = null;
            }
        }

    }

    static class UsingPrintStream extends StateBase {

        final File   file;
        final String encoding;
        PrintStream  out;

        public UsingPrintStream(File file, String encoding) {
            super("OutputStream"); //$NON-NLS-1$
            this.file = file;
            this.encoding = encoding;
        }

        @Override
        public PrintStream getOut() throws IOException {
            if (out == null) {
                OutputStream _out = new FileOutputStream(file);
                if (encoding == null)
                    out = new PrintStream(_out);
                else
                    out = new PrintStream(_out, true, encoding);
            }
            return out;
        }

        @Override
        public void close() throws IOException {
            if (out != null) {
                out.close();
                out = null;
            }
        }

    }

    static class UsingWriter extends StateBase {

        final File   file;
        final String encoding;
        Writer       writer;

        public UsingWriter(File file, String encoding) {
            super("Writer"); //$NON-NLS-1$
            this.file = file;
            this.encoding = encoding;
        }

        @Override
        public Writer getWriter() throws IOException {
            if (writer == null) {
                if (encoding == null)
                    writer = new FileWriter(file);
                else {
                    OutputStream out = new FileOutputStream(file);
                    writer = new OutputStreamWriter(out, encoding);
                }
            }
            return writer;
        }

        @Override
        public void close() throws IOException {
            if (writer != null) {
                writer.close();
                writer = null;
            }
        }

    }

    static class UsingObjectOutputStream extends StateBase {

        final File         file;
        ObjectOutputStream objout;

        public UsingObjectOutputStream(File file) {
            super("ObjectOutputStream"); //$NON-NLS-1$
            this.file = file;
        }

        @Override
        public ObjectOutputStream getObjectOut() throws IOException {
            if (objout == null) {
                OutputStream out = new FileOutputStream(file);
                objout = new ObjectOutputStream(out);
            }
            return objout;
        }

        @Override
        public void close() throws IOException {
            if (objout != null) {
                objout.close();
                objout = null;
            }
        }

    }

    static class UsingZipOutputStream extends StateBase {

        final File      file;
        ZipOutputStream zout;

        public UsingZipOutputStream(File file) {
            super("ZipOutputStream"); //$NON-NLS-1$
            this.file = file;
        }

        @Override
        public ZipOutputStream getZipOut() throws IOException {
            if (zout == null) {
                OutputStream out = new FileOutputStream(file);
                zout = new ZipOutputStream(out);
            }
            return zout;
        }

        @Override
        public void close() throws IOException {
            if (zout != null) {
                zout.close();
                zout = null;
            }
        }

    }

    static class UsingJarOutputStream extends StateBase {

        final File      file;
        JarOutputStream jout;

        public UsingJarOutputStream(File file) {
            super("JarOutputStream"); //$NON-NLS-1$
            this.file = file;
        }

        @Override
        public ZipOutputStream getZipOut() throws IOException {
            if (jout == null) {
                OutputStream out = new FileOutputStream(file);
                jout = new JarOutputStream(out);
            }
            return jout;
        }

        @Override
        public void close() throws IOException {
            if (jout != null) {
                jout.close();
                jout = null;
            }
        }

    }

}
