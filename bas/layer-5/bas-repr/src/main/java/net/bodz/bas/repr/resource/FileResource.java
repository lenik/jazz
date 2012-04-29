package net.bodz.bas.repr.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileResource
        extends AbstractResource {

    private File file;

    public FileResource(String filename) {
        this(new File(filename));
    }

    public FileResource(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public String getPath() {
        return file.getPath();
    }

    @Override
    public InputStream openBinary()
            throws IOException {
        return new FileInputStream(file);
    }

}
