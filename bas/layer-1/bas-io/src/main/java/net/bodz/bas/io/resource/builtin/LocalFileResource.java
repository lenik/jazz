package net.bodz.bas.io.resource.builtin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.exceptions.BadFormatException;
import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.io.resource.IRandomInputSource;
import net.bodz.bas.io.resource.IRandomOutputTarget;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ILineCharOut;
import net.bodz.bas.sio.InputStreamByteIn;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.sio.ReaderCharIn;
import net.bodz.bas.sio.WriterCharOut;

public class LocalFileResource
        extends AbstractStreamResource
        implements IRandomInputSource, IRandomOutputTarget {

    private final File file;

    public LocalFileResource(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public LocalFileResource(String filename) {
        this(new File(filename));
    }

    @Override
    public InputStream newInputStream()
            throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        return new FileOutputStream(file);
    }

    @Override
    public Reader newReader()
            throws IOException {
        InputStream inputStream = newInputStream();
        if (inputStream == null)
            throw new NullPointerException("inputStream");
        Charset charset = getCharset();
        return new InputStreamReader(inputStream, charset);
    }

    @Override
    public Writer newWriter()
            throws IOException {
        OutputStream outputStream = newOutputStream();
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        Charset charset = getCharset();
        return new OutputStreamWriter(outputStream, charset);
    }

    @Override
    public IByteIn newByteIn()
            throws IOException {
        InputStream inputStream = newInputStream();
        if (inputStream == null)
            throw new NullPointerException("inputStream");
        return new InputStreamByteIn(inputStream);
    }

    @Override
    public IByteOut newByteOut()
            throws IOException {
        OutputStream outputStream = newOutputStream();
        if (outputStream == null)
            throw new NullPointerException("outputStream");
        return new OutputStreamByteOut(outputStream);
    }

    @Override
    public ICharIn newCharIn()
            throws IOException {
        Reader reader = newReader();
        if (reader == null)
            throw new NullPointerException("reader");
        return new ReaderCharIn(reader);
    }

    @Override
    public ILineCharOut newCharOut()
            throws IOException {
        Writer writer = newWriter();
        if (writer == null)
            throw new NullPointerException("writer");
        return new WriterCharOut(writer);
    }

    // private AbstractRandomInputSource arisImpl;
    // private AbstractRandomOutputTarget arotImpl;

    @Override
    public JarFile newJarFile()
            throws BadFormatException, IOException {
        return new JarFile(file);
    }

    @Override
    public ZipFile newZipFile()
            throws BadFormatException, IOException {
        return new ZipFile(file);
    }

}
