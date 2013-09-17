package net.bodz.pkg.installer;

import java.io.*;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public interface Attachment {

    Reader getReader()
            throws IOException;

    Writer getWriter()
            throws IOException;

    InputStream getIn()
            throws IOException;

    PrintStream getOut()
            throws IOException;

    OutputStream _getOut()
            throws IOException;

    ObjectInputStream getObjectIn()
            throws IOException;

    ObjectOutputStream getObjectOut()
            throws IOException;

    ZipInputStream getZipIn()
            throws IOException;

    ZipOutputStream getZipOut()
            throws IOException;

    JarInputStream getJarIn()
            throws IOException;

    JarOutputStream getJarOut()
            throws IOException;

    /**
     * Depend on the content type of attachment.
     */
    Object load()
            throws IOException;

    void close()
            throws IOException;

}
