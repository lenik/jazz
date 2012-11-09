package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.vfs.path.DefaultPath;
import net.bodz.bas.vfs.path.align.IPathAlignment;

public class PseudoPath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    private PseudoFile file;

    public PseudoPath(String localPath, PseudoFile file) {
        super(PseudoVfsDevice.getInstance(), localPath, IPathAlignment.ROOT);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public PseudoFile resolve() {
        return file;
    }

}