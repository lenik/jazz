package net.bodz.bas.vfs.impl.nio;

import java.nio.file.Path;

import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFsDir;

/**
 * @see LocalFileResource
 */
public class NioFile
        extends AbstractFile
        implements IFsDir {

    private final Path path;

}
