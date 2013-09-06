package net.bodz.redist.installer;

import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.vfs.IFile;

public class StatedAttachment
        implements Attachment, II18nCapable {

    private final IFile link;
    private final String encoding;

    private StateBase stated;

    public StatedAttachment(IFile link) {
        this(link, null);
    }

    public StatedAttachment(IFile link, String encoding) {
        if (link == null)
            throw new NullPointerException("link");
        this.link = link;
        this.encoding = encoding;
    }

    @Override
    public Reader getReader()
            throws IOException {
        if (stated == null)
            stated = new UsingReader(link, encoding);
        return stated.getReader();
    }

    @Override
    public Writer getWriter()
            throws IOException {
        if (stated == null)
            stated = new UsingWriter(link, encoding);
        return stated.getWriter();
    }

    @Override
    public InputStream getIn()
            throws IOException {
        if (stated == null)
            stated = new UsingInputStream(link);
        return stated.getIn();
    }

    @Override
    public PrintStream getOut()
            throws IOException {
        if (stated == null)
            stated = new UsingPrintStream(link, encoding);
        return stated.getOut();
    }

    @Override
    public OutputStream _getOut()
            throws IOException {
        if (stated == null)
            stated = new UsingOutputStream(link);
        return stated._getOut();
    }

    @Override
    public ObjectInputStream getObjectIn()
            throws IOException {
        if (stated == null)
            stated = new UsingObjectInputStream(link);
        return stated.getObjectIn();
    }

    @Override
    public ObjectOutputStream getObjectOut()
            throws IOException {
        if (stated == null)
            stated = new UsingObjectOutputStream(link);
        return stated.getObjectOut();
    }

    @Override
    public ZipFile getZipFile()
            throws IOException {
        if (stated == null)
            stated = new UsingZipFile(link);
        return stated.getZipFile();
    }

    @Override
    public ZipInputStream getZipIn()
            throws IOException {
        if (stated == null)
            stated = new UsingZipInputStream(link);
        return stated.getZipIn();
    }

    @Override
    public ZipOutputStream getZipOut()
            throws IOException {
        if (stated == null)
            stated = new UsingZipOutputStream(link);
        return stated.getZipOut();
    }

    @Override
    public JarFile getJarFile()
            throws IOException {
        if (stated == null)
            stated = new UsingJarFile(link);
        return stated.getJarFile();
    }

    @Override
    public JarInputStream getJarIn()
            throws IOException {
        if (stated == null)
            stated = new UsingJarInputStream(link);
        return stated.getJarIn();
    }

    @Override
    public JarOutputStream getJarOut()
            throws IOException {
        if (stated == null)
            stated = new UsingJarOutputStream(link);
        return stated.getJarOut();
    }

    @Override
    public Object load()
            throws IOException {
        throw new NotImplementedException("don't know how to load.");
    }

    @Override
    public void close()
            throws IOException {
        if (stated != null) {
            stated.close();
            stated = null;
        }
    }

    @Override
    public String toString() {
        String s = tr._("attachment ");
        s += link.toString();
        return s;
    }

    public abstract static class StateBase
            implements Attachment {

        protected final IFile link;
        protected final String error;

        public StateBase(IFile link, String accessType) {
            this.link = link;
            this.error = tr._("Currently opened using ") + accessType;
        }

        @Override
        public Reader getReader()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public Writer getWriter()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public InputStream getIn()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public PrintStream getOut()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public OutputStream _getOut()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ObjectInputStream getObjectIn()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ObjectOutputStream getObjectOut()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ZipFile getZipFile()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ZipInputStream getZipIn()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public ZipOutputStream getZipOut()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public JarFile getJarFile()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public JarInputStream getJarIn()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public JarOutputStream getJarOut()
                throws IOException {
            throw new IllegalStateException(error);
        }

        @Override
        public Object load()
                throws IOException {
            throw new IllegalStateException(error);
        }
    }

    static class UsingInputStream
            extends StateBase {

        InputStream in;

        public UsingInputStream(IFile link) {
            super(link, "InputStream");
        }

        @Override
        public InputStream getIn()
                throws IOException {
            if (in == null)
                in = link.getInputSource().newInputStream();
            return in;
        }

        @Override
        public void close()
                throws IOException {
            if (in != null) {
                in.close();
                in = null;
            }
        }

    }

    static class UsingReader
            extends StateBase {

        final String encoding;
        Reader reader;

        public UsingReader(IFile link, String encoding) {
            super(link, "Reader");
            this.encoding = encoding;
        }

        @Override
        public Reader getReader()
                throws IOException {
            if (reader == null)
                reader = link.getInputSource(encoding).newReader();
            return reader;
        }

        @Override
        public void close()
                throws IOException {
            if (reader != null) {
                reader.close();
                reader = null;
            }
        }

    }

    static class UsingObjectInputStream
            extends StateBase {

        ObjectInputStream objin;

        public UsingObjectInputStream(IFile link) {
            super(link, "ObjectInputStream");
        }

        @Override
        public ObjectInputStream getObjectIn()
                throws IOException {
            if (objin == null) {
                InputStream in = link.getInputSource().newInputStream();
                objin = new ObjectInputStream(in);
            }
            return objin;
        }

        @Override
        public void close()
                throws IOException {
            if (objin != null) {
                objin.close();
                objin = null;
            }
        }

    }

    static class UsingZipInputStream
            extends StateBase {

        ZipInputStream zin;

        public UsingZipInputStream(IFile link) {
            super(link, "ZipInputStream");
        }

        @Override
        public ZipInputStream getZipIn()
                throws IOException {
            if (zin == null) {
                InputStream in = link.getInputSource().newInputStream();
                zin = new ZipInputStream(in);
            }
            return zin;
        }

        @Override
        public void close()
                throws IOException {
            if (zin != null) {
                zin.close();
                zin = null;
            }
        }

    }

    static class UsingJarInputStream
            extends StateBase {

        JarInputStream jin;

        public UsingJarInputStream(IFile link) {
            super(link, "JarInputStream");
        }

        @Override
        public JarInputStream getJarIn()
                throws IOException {
            if (jin == null) {
                InputStream in = link.getInputSource().newInputStream();
                jin = new JarInputStream(in);
            }
            return jin;
        }

        @Override
        public void close()
                throws IOException {
            if (jin != null) {
                jin.close();
                jin = null;
            }
        }

    }

    static class UsingOutputStream
            extends StateBase {

        OutputStream out;

        public UsingOutputStream(IFile link) {
            super(link, "OutputStream");
        }

        @Override
        public OutputStream _getOut()
                throws IOException {
            if (out == null) {
                IStreamOutputTarget outputTarget = link.getOutputTarget();
                out = outputTarget.newOutputStream();
            }
            return out;
        }

        @Override
        public void close()
                throws IOException {
            if (out != null) {
                out.close();
                out = null;
            }
        }

    }

    static class UsingPrintStream
            extends StateBase {

        final String encoding;
        PrintStream out;

        public UsingPrintStream(IFile link, String encoding) {
            super(link, "OutputStream");
            this.encoding = encoding;
        }

        @Override
        public PrintStream getOut()
                throws IOException {
            if (out == null) {
                IStreamOutputTarget outputTarget = link.getOutputTarget();
                OutputStream _out = outputTarget.newOutputStream();
                if (encoding == null)
                    out = new PrintStream(_out);
                else
                    out = new PrintStream(_out, true, encoding);
            }
            return out;
        }

        @Override
        public void close()
                throws IOException {
            if (out != null) {
                out.close();
                out = null;
            }
        }

    }

    static class UsingWriter
            extends StateBase {

        final String encoding;
        Writer writer;

        public UsingWriter(IFile link, String encoding) {
            super(link, "Writer");
            this.encoding = encoding;
        }

        @Override
        public Writer getWriter()
                throws IOException {
            if (writer == null)
                writer = link.getOutputTarget(encoding).newWriter();
            return writer;
        }

        @Override
        public void close()
                throws IOException {
            if (writer != null) {
                writer.close();
                writer = null;
            }
        }

    }

    static class UsingObjectOutputStream
            extends StateBase {

        ObjectOutputStream objout;

        public UsingObjectOutputStream(IFile link) {
            super(link, "ObjectOutputStream");
        }

        @Override
        public ObjectOutputStream getObjectOut()
                throws IOException {
            if (objout == null) {
                OutputStream out = link.getOutputTarget().newOutputStream();
                objout = new ObjectOutputStream(out);
            }
            return objout;
        }

        @Override
        public void close()
                throws IOException {
            if (objout != null) {
                objout.close();
                objout = null;
            }
        }

    }

    static class UsingZipOutputStream
            extends StateBase {

        ZipOutputStream zout;

        public UsingZipOutputStream(IFile link) {
            super(link, "ZipOutputStream");
        }

        /**
         * JDK doesn't support to append a zip file.
         */
        @Override
        public ZipOutputStream getZipOut()
                throws IOException {
            if (zout == null) {
                OutputStream out = link.getOutputTarget().newOutputStream();
                zout = new ZipOutputStream(out);
            }
            return zout;
        }

        @Override
        public void close()
                throws IOException {
            if (zout != null) {
                zout.close();
                zout = null;
            }
        }

    }

    static class UsingJarOutputStream
            extends StateBase {

        JarOutputStream jout;

        public UsingJarOutputStream(IFile link) {
            super(link, "JarOutputStream");
        }

        /**
         * JDK doesn't support to append a zip file.
         */
        @Override
        public JarOutputStream getJarOut()
                throws IOException {
            if (jout == null) {
                OutputStream out = link.getOutputTarget().newOutputStream(StandardOpenOption.APPEND);
                jout = new JarOutputStream(out);
            }
            return jout;
        }

        @Override
        public void close()
                throws IOException {
            if (jout != null) {
                jout.close();
                jout = null;
            }
        }

    }

    static class UsingZipFile
            extends StateBase {

        ZipFile zipFile;

        public UsingZipFile(IFile link) {
            super(link, "ZipFile");
        }

        @Override
        public ZipFile getZipFile()
                throws IOException {
            if (zipFile == null) {
                zipFile = link.getInputSource().newZipFile();
            }
            return zipFile;
        }

        @Override
        public void close()
                throws IOException {
            if (zipFile != null) {
                zipFile.close();
                zipFile = null;
            }
        }

    }

    static class UsingJarFile
            extends StateBase {

        JarFile jarFile;

        public UsingJarFile(IFile link) {
            super(link, "JarFile");
        }

        @Override
        public JarFile getJarFile()
                throws IOException {
            if (jarFile == null) {
                jarFile = link.getInputSource().newJarFile();
            }
            return jarFile;
        }

        @Override
        public void close()
                throws IOException {
            if (jarFile != null) {
                jarFile.close();
                jarFile = null;
            }
        }

    }

}
