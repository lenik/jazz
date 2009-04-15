package net.bodz.bas.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FileResLink extends _ResLink {

    private File    file;
    private boolean autoMkdirs;

    public FileResLink(File file) {
        if (file == null)
            throw new NullPointerException("file");
        if (file.exists() && !file.isFile())
            throw new IllegalStateException(
                    "Non-file with the same path is already existed: " + file);
        this.file = file;
    }

    public boolean isAutoMkdirs() {
        return autoMkdirs;
    }

    public void setAutoMkdirs(boolean autoMkdirs) {
        this.autoMkdirs = autoMkdirs;
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

}
