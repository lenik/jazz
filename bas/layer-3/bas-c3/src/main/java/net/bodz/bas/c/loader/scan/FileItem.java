package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import net.bodz.bas.err.UnexpectedException;

public class FileItem
        implements
            IResourceItem {

    File file;
    String relativePath;

    public FileItem(File file, String relativePath) {
        this.file = file;
        this.relativePath = relativePath;
    }

    @Override
    public String getRelativePath() {
        return relativePath;
    }

//    @Override
//    public String getName() {
//        return file.getName();
//    }

    @Override
    public URI toURI() {
        return file.toURI();
    }

    @Override
    public URL toURL() {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return file.toString();
    }

}
