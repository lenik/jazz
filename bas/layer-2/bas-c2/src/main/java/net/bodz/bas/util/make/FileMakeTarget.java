package net.bodz.bas.util.make;

import java.io.File;
import java.util.Collection;

public class FileMakeTarget
        extends AbstractMakeTarget {

    private final File file;

    public FileMakeTarget(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public FileMakeTarget(File file, Collection<? extends IMakeTarget> dependencies) {
        super(dependencies);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public FileMakeTarget(File file, IMakeTarget... dependencies) {
        super(dependencies);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public long getVersion() {
        return file.lastModified();
    }

    @Override
    public String getName() {
        return file.getName();
    }

}
