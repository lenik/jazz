package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.FileFilter;
import java.util.zip.ZipEntry;

public interface IFileOrEntryFilter
        extends
            FileFilter {

    boolean accept(ZipEntry entry);

    IFileOrEntryFilter ALL = new IFileOrEntryFilter() {

        @Override
        public boolean accept(File pathname) {
            return true;
        }

        @Override
        public boolean accept(ZipEntry entry) {
            return true;
        }
    };

}
