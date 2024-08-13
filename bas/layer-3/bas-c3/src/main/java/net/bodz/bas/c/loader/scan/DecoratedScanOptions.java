package net.bodz.bas.c.loader.scan;

import java.io.File;
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
    public boolean acceptDirPath(File dir) {
        return getWrapped().acceptDirPath(dir);
    }

    @Override
    public boolean acceptJarPath(File jar) {
        return getWrapped().acceptJarPath(jar);
    }

    @Override
    public boolean acceptLocalEntry(File startDir, File file) {
        return getWrapped().acceptLocalEntry(startDir, file);
    }

    @Override
    public boolean acceptZipEntry(ZipEntry entry) {
        return getWrapped().acceptZipEntry(entry);
    }

}
