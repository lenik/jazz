package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.FileFilter;
import java.util.zip.ZipEntry;

public interface IScanOptions {

    default FileFilter getFileFilter(File startDir) {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return acceptLocalEntry(startDir, pathname);
            }
        };
    }

    boolean acceptDirPath(File dir);

    boolean acceptJarPath(File jar);

    boolean acceptLocalEntry(File startDir, File file);

    boolean acceptZipEntry(ZipEntry entry);

    IScanOptions ALL = new IScanOptions() {

        @Override
        public boolean acceptDirPath(File dir) {
            return true;
        }

        @Override
        public boolean acceptJarPath(File jar) {
            return true;
        }

        @Override
        public boolean acceptLocalEntry(File startDir, File file) {
            return true;
        }

        @Override
        public boolean acceptZipEntry(ZipEntry entry) {
            return true;
        }

    };

}
