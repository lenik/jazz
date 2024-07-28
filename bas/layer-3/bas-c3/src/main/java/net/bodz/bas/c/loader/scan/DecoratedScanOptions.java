package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedScanOptions
        extends AbstractDecorator<IScanOptions>
        implements
            IScanOptions {

    private static final long serialVersionUID = 1L;

    public DecoratedScanOptions(IScanOptions _orig) {
        super(_orig);
    }

    @Override
    public boolean acceptDirEntry(File dir) {
        return getWrapped().acceptDirEntry(dir);
    }

    @Override
    public boolean acceptDirEntry(ZipEntry entry) {
        return getWrapped().acceptDirEntry(entry);
    }

    @Override
    public boolean acceptDirEntry(String name) {
        return getWrapped().acceptDirEntry(name);
    }

    @Override
    public boolean acceptFileEntry(File file) {
        return getWrapped().acceptFileEntry(file);
    }

    @Override
    public boolean acceptFileEntry(ZipEntry entry) {
        return getWrapped().acceptFileEntry(entry);
    }

    @Override
    public boolean acceptFileEntry(String name) {
        return getWrapped().acceptFileEntry(name);
    }

    @Override
    public boolean acceptJarPath(JarFile jarFile) {
        return getWrapped().acceptJarPath(jarFile);
    }

    @Override
    public boolean acceptPath(File dir) {
        return getWrapped().acceptPath(dir);
    }

    @Override
    public boolean acceptPath(String dirName) {
        return getWrapped().acceptPath(dirName);
    }

}
