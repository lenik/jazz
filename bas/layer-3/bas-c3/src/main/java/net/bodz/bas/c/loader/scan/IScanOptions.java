package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.FileFilter;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public interface IScanOptions {

    default FileFilter getFileFilter() {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    if (! acceptDirEntry(pathname))
                        return false;
                    if (! acceptDirName(pathname.getName()))
                        return false;
                } else {
                    if (! acceptFileEntry(pathname))
                        return false;
                    if (! acceptFileName(pathname.getName()))
                        return false;
                }
                return true;
            }
        };
    }

    default boolean acceptZipEntry(ZipEntry entry) {
        if (entry.isDirectory()) {
            if (! acceptDirEntry(entry))
                return false;
            if (! acceptDirName(entry.getName()))
                return false;
        } else {
            if (! acceptFileEntry(entry))
                return false;
            if (! acceptFileName(entry.getName()))
                return false;
        }
        return true;
    }

    boolean acceptDirEntry(File dir);

    boolean acceptDirEntry(ZipEntry entry);

    boolean acceptDirName(String name);

    boolean acceptFileEntry(File file);

    boolean acceptFileEntry(ZipEntry entry);

    boolean acceptFileName(String name);

    boolean acceptJarPath(JarFile jarFile);

    boolean acceptPath(File dir);

    boolean acceptPath(String dirName);

    IScanOptions ALL = new IScanOptions() {

        @Override
        public boolean acceptDirEntry(File dir) {
            return true;
        }

        @Override
        public boolean acceptDirEntry(ZipEntry entry) {
            return true;
        }

        @Override
        public boolean acceptDirName(String name) {
            return true;
        }

        @Override
        public boolean acceptFileEntry(File file) {
            return true;
        }

        @Override
        public boolean acceptFileEntry(ZipEntry entry) {
            return true;
        }

        @Override
        public boolean acceptFileName(String name) {
            return true;
        }

        @Override
        public boolean acceptJarPath(JarFile jarFile) {
            return true;
        }

        @Override
        public boolean acceptPath(File dir) {
            return true;
        }

        @Override
        public boolean acceptPath(String dirName) {
            return true;
        }

    };

}
