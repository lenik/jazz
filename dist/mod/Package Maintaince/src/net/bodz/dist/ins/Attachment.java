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

public interface Attachment {

    Reader getReader() throws IOException;

    Writer getWriter() throws IOException;

    InputStream getIn() throws IOException;

    PrintStream getOut() throws IOException;

    OutputStream _getOut() throws IOException;

    ObjectInputStream getObjectIn() throws IOException;

    ObjectOutputStream getObjectOut() throws IOException;

    ZipFile getZipFile() throws IOException;

    ZipInputStream getZipIn() throws IOException;

    ZipOutputStream getZipOut() throws IOException;

    JarFile getJarFile() throws IOException;

    JarInputStream getJarIn() throws IOException;

    JarOutputStream getJarOut() throws IOException;

    /**
     * Depend on the content type of attachment.
     */
    Object load() throws IOException;

    void close() throws IOException;

}
