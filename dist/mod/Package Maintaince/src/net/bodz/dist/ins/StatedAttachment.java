package net.bodz.dist.ins;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import net.bodz.bas.io.ResLink;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.dist.nls.PackNLS;

public class StatedAttachment implements Attachment {

    private final ResLink link;
    private final String  encoding;

    private StateBase     stated;

    public StatedAttachment(ResLink link) {
        this(link, null);
    }

    public StatedAttachment(ResLink link, String encoding) {
        if (link == null)
            throw new NullPointerException("link"); //$NON-NLS-1$
        this.link = link;
        this.encoding = encoding;
    }

    @Override
    public Reader getReader() throws IOException {
        if (stated == null)
            stated = new UsingReader(link, encoding);
        return stated.getReader();
    }

    @Override
    public Writer getWriter() throws IOException {
        if (stated == null)
            stated = new UsingWriter(link, encoding);
        return stated.getWriter();
    }

    @Override
    public InputStream getIn() throws IOException {
        if (stated == null)
            stated = new UsingInputStream(link);
        return stated.getIn();
    }

    @Override
    public PrintStream getOut() throws IOException {
        if (stated == null)
            stated = new UsingPrintStream(link, encoding);
        return stated.getOut();
    }

    @Override
    public OutputStream _getOut() throws IOException {
        if (stated == null)
            stated = new UsingOutputStream(link);
        return stated._getOut();
    }

    @Override
    public ObjectInputStream getObjectIn() throws IOException {
        if (stated == null)
            stated = new UsingObjectInputStream(link);
        return stated.getObjectIn();
    }

    @Override
    public ObjectOutputStream getObjectOut() throws IOException {
        if (stated == null)
            stated = new UsingObjectOutputStream(link);
        return stated.getObjectOut();
    }

    @Override
    public ZipFile getZipFile() throws IOException {
        if (stated == null)
            stated = new UsingZipFile(link);
        return stated.getZipFile();
    }

    @Override
    public ZipInputStream getZipIn() throws IOException {
        if (stated == null)
            stated = new UsingZipInputStream(link);
        return stated.getZipIn();
    }

    @Override
    public ZipOutputStream getZipOut() throws IOException {
        if (stated == null)
            stated = new UsingZipOutputStream(link);
        return stated.getZipOut();
    }

    @Override
    public JarFile getJarFile() throws IOException {
        if (stated == null)
            stated = new UsingJarFile(link);
        return stated.getJarFile();
    }

    @Override
    public JarInputStream getJarIn() throws IOException {
        if (stated == null)
            stated = new UsingJarInputStream(link);
        return stated.getJarIn();
    }

    @Override
    public JarOutputStream getJarOut() throws IOException {
        if (stated == null)
            stated = new UsingJarOutputStream(link);
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
        s += link.toString();
        return s;
    }

    public abstract static class StateBase implements Attachment {

        protected final ResLink link;
        protected final String  error;

        public StateBase(ResLink link, String accessType) {
            this.link = link;
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
        public ZipFile getZipFile() throws IOException {
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
        public JarFile getJarFile() throws IOException {
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

        InputStream in;

        public UsingInputStream(ResLink link) {
            super(link, "InputStream"); //$NON-NLS-1$
        }

        @Override
        public InputStream getIn() throws IOException {
            if (in == null)
                in = link.openInputStream();
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

        final String encoding;
        Reader       reader;

        public UsingReader(ResLink link, String encoding) {
            super(link, "Reader"); //$NON-NLS-1$
            this.encoding = encoding;
        }

        @Override
        public Reader getReader() throws IOException {
            if (reader == null)
                reader = link.openReader(encoding);
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

        ObjectInputStream objin;

        public UsingObjectInputStream(ResLink link) {
            super(link, "ObjectInputStream"); //$NON-NLS-1$
        }

        @Override
        public ObjectInputStream getObjectIn() throws IOException {
            if (objin == null) {
                InputStream in = link.openInputStream();
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

        ZipInputStream zin;

        public UsingZipInputStream(ResLink link) {
            super(link, "ZipInputStream"); //$NON-NLS-1$
        }

        @Override
        public ZipInputStream getZipIn() throws IOException {
            if (zin == null) {
                InputStream in = link.openInputStream();
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

        JarInputStream jin;

        public UsingJarInputStream(ResLink link) {
            super(link, "JarInputStream"); //$NON-NLS-1$
        }

        @Override
        public JarInputStream getJarIn() throws IOException {
            if (jin == null) {
                InputStream in = link.openInputStream();
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

        OutputStream out;

        public UsingOutputStream(ResLink link) {
            super(link, "OutputStream"); //$NON-NLS-1$
        }

        @Override
        public OutputStream _getOut() throws IOException {
            if (out == null)
                out = link.openOutputStream(false);
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

        final String encoding;
        PrintStream  out;

        public UsingPrintStream(ResLink link, String encoding) {
            super(link, "OutputStream"); //$NON-NLS-1$
            this.encoding = encoding;
        }

        @Override
        public PrintStream getOut() throws IOException {
            if (out == null) {
                OutputStream _out = link.openOutputStream(false);
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

        final String encoding;
        Writer       writer;

        public UsingWriter(ResLink link, String encoding) {
            super(link, "Writer"); //$NON-NLS-1$
            this.encoding = encoding;
        }

        @Override
        public Writer getWriter() throws IOException {
            if (writer == null)
                writer = link.openWriter(false, encoding);
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

        ObjectOutputStream objout;

        public UsingObjectOutputStream(ResLink link) {
            super(link, "ObjectOutputStream"); //$NON-NLS-1$
        }

        @Override
        public ObjectOutputStream getObjectOut() throws IOException {
            if (objout == null) {
                OutputStream out = link.openOutputStream(false);
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

        ZipOutputStream zout;

        public UsingZipOutputStream(ResLink link) {
            super(link, "ZipOutputStream"); //$NON-NLS-1$
        }

        @Override
        public ZipOutputStream getZipOut() throws IOException {
            if (zout == null) {
                OutputStream out = link.openOutputStream(true); // false);
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

        JarOutputStream jout;

        public UsingJarOutputStream(ResLink link) {
            super(link, "JarOutputStream"); //$NON-NLS-1$
        }

        @Override
        public JarOutputStream getJarOut() throws IOException {
            if (jout == null) {
                OutputStream out = link.openOutputStream(true); // XXX - false);
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

    static class UsingZipFile extends StateBase {

        ZipFile zipFile;

        public UsingZipFile(ResLink link) {
            super(link, "ZipFile"); //$NON-NLS-1$
        }

        @Override
        public ZipFile getZipFile() throws IOException {
            if (zipFile == null) {
                zipFile = link.openZipFile();
            }
            return zipFile;
        }

        @Override
        public void close() throws IOException {
            if (zipFile != null) {
                zipFile.close();
                zipFile = null;
            }
        }

    }

    static class UsingJarFile extends StateBase {

        JarFile jarFile;

        public UsingJarFile(ResLink link) {
            super(link, "JarFile"); //$NON-NLS-1$
        }

        @Override
        public JarFile getJarFile() throws IOException {
            if (jarFile == null) {
                jarFile = link.openJarFile();
            }
            return jarFile;
        }

        @Override
        public void close() throws IOException {
            if (jarFile != null) {
                jarFile.close();
                jarFile = null;
            }
        }

    }

}
