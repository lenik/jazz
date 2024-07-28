package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class AbstractScanOptions
        implements
            IScanOptions {

    @Override
    public boolean acceptDirEntry(File dir) {
        String name = dir.getName();
        if (! acceptDirEntry(name))
            return false;
        return true;
    }

    @Override
    public boolean acceptDirEntry(ZipEntry entry) {
        String name = entry.getName();
        if (! acceptDirEntry(name))
            return false;
        return true;
    }

    @Override
    public boolean acceptDirEntry(String name) {
        return true;
    }

    @Override
    public boolean acceptFileEntry(File file) {
        if (file.isFile()) {
            String name = file.getName();
            if (! acceptFileEntry(name))
                return false;
        }
        return true;
    }

    @Override
    public boolean acceptFileEntry(ZipEntry entry) {
        if (! entry.isDirectory()) {
            String name = entry.getName();
            if (! acceptFileEntry(name))
                return false;
        }
        return true;
    }

    @Override
    public boolean acceptFileEntry(String name) {
        return true;
    }

    @Override
    public boolean acceptJarPath(JarFile jarFile) {
        return true;
    }

    @Override
    public boolean acceptPath(File dir) {
        String name = dir.getName();
        if (! acceptPath(name))
            return false;
        return true;
    }

    @Override
    public boolean acceptPath(String dirName) {
        return true;
    }

}
