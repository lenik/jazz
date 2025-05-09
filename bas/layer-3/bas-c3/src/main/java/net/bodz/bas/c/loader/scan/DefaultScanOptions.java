package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.zip.ZipEntry;

import net.bodz.bas.io.PathPatternList;

public class DefaultScanOptions
        extends AbstractScanOptions {

    PathPatternList pathIncludes = new PathPatternList();
    PathPatternList pathExcludes = new PathPatternList();

    PathPatternList entryExcludes = new PathPatternList();

    boolean excludeJarPaths = true;

    public DefaultScanOptions() {
        entryExcludes.addBaseName("node_modules");
        entryExcludes.addBaseName("libjs");
    }

    public void includePath(String dirPath) {
        pathIncludes.addStartDir(dirPath);
    }

    public void excludePath(String dirPath) {
        pathExcludes.addStartDir(dirPath);
    }

    @Override
    public boolean acceptDirPath(File dir) {
        if (pathExcludes.anyAccept(dir))
            return false;
        return true;
    }

    @Override
    public boolean acceptJarPath(File jar) {
        if (excludeJarPaths)
            return false;
        return true;
    }

    @Override
    public boolean acceptLocalEntry(File startDir, File file) {
        if (file.isDirectory()) {
            if (pathExcludes.anyAccept(file, false))
                return false;
            if (! pathIncludes.anyAccept(file, true))
                return false;
        }
        if (entryExcludes.anyAccept(file, false))
            return false;
        return true;
    }

    @Override
    public boolean acceptZipEntry(ZipEntry entry) {
        return true;
    }

}
