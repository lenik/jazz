package net.bodz.bas.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.nls.SysNLS;

public class FileResLink extends _ResLink {

    private final File file;
    private final boolean autoMkdirs;

    public FileResLink(String path) {
        this(new File(path));
    }

    public FileResLink(File file) {
        this(file, false);
    }

    public FileResLink(File file, boolean autoMkdirs) {
        if (file == null)
            throw new NullPointerException("file"); //$NON-NLS-1$
        if (file.exists() && !file.isFile())
            throw new IllegalStateException(SysNLS.getString("FileResLink.nonfileWithSameName") //$NON-NLS-1$
                    + file);
        this.file = file;
        this.autoMkdirs = autoMkdirs;
    }

    public boolean isAutoMkdirs() {
        return autoMkdirs;
    }

    void autoMkdirs() {
        if (autoMkdirs) {
            File parentDir = file.getParentFile();
            if (parentDir != null)
                parentDir.mkdirs();
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public URL getURL() {
        return Files.getURL(file);
    }

    @Override
    public Boolean exists() {
        return file.exists();
    }

    @Override
    public InputStream openInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public OutputStream openOutputStream(boolean append) throws IOException {
        autoMkdirs();
        return new FileOutputStream(file, append);
    }

    @Override
    public ZipFile openZipFile() throws IOException {
        return new ZipFile(file);
    }

    @Override
    public JarFile openJarFile() throws IOException {
        return new JarFile(file);
    }

    @Override
    public String toString() {
        String s = file.getPath();
        if (autoMkdirs)
            s = "*" + s;
        return s;
    }

}
