package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.zip.ZipEntry;

public class ScanOptions {

    public static IScanOptions classOnly(IScanOptions options) {
        return new DecoratedScanOptions(options) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean acceptLocalEntry(File startDir, File file) {
                if (file.isDirectory())
                    return true;
                if (! acceptName(file.getName()))
                    return false;
                return super.acceptLocalEntry(startDir, file);
            }

            @Override
            public boolean acceptZipEntry(ZipEntry entry) {
                if (! acceptName(entry.getName()))
                    return false;
                return super.acceptZipEntry(entry);
            }

            boolean acceptName(String name) {
                return name.endsWith(".class");
            }
        };
    }

}
