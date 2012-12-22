package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public interface IFileOrEntryProcessor {

    void process(File file, String resourceName);

    void process(ZipFile file, ZipEntry entry);

}
