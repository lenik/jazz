package net.bodz.bas.vfs.impl.pseudo;

import net.bodz.bas.vfs.IVfsDevice;
import net.bodz.bas.vfs.path.DefaultPath;

public class PseudoPath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    private PseudoFile file;

    public PseudoPath(IVfsDevice device, String localPath, PseudoFile file) {
        super(device, localPath);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public PseudoFile resolve() {
        return file;
    }

}
