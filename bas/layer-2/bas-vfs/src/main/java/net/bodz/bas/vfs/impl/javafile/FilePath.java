package net.bodz.bas.vfs.impl.javafile;

import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.align.IPathAlignment;

public class FilePath
        extends AbstractPath {

    public FilePath(IVolume volume, String localPath, IPathAlignment alignment) {
        super(volume, localPath, alignment);
    }

}
