package net.bodz.bas.vfs.path;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVfsDevice;
import net.bodz.bas.vfs.path.align.IPathAlignment;
import net.bodz.bas.vfs.path.align.ParentAlignment;

public class RelativePath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    public RelativePath(String localPath, IPathAlignment alignment) {
        super(null /* dev */, localPath, alignment);
    }

    public RelativePath(String localPath, int parents) {
        this(localPath, new ParentAlignment(parents));
    }

    @Override
    public IVfsDevice getDevice() {
        return null;
    }

    @Override
    public IFile resolve() {
        throw new IllegalUsageException("You can't resolve a relative path.");
    }

    public static RelativePath parse(String decoratedPath) {
        IPathAlignment alignment;
        String localPath;

        if (decoratedPath.startsWith("/")) {
            alignment = IPathAlignment.ROOT;
            if (decoratedPath.startsWith("//"))
                alignment = IPathAlignment.ROOT_LAYER;

            int nonSlash = 1;
            while (decoratedPath.charAt(nonSlash) == '/')
                nonSlash++;

            localPath = decoratedPath.substring(nonSlash);

        } else if (decoratedPath.startsWith("../")) {

            int parents = 1;
            int dds = 3;
            while (decoratedPath.substring(dds, 3).equals("../")) {
                parents++;
                dds += 3;
            }

            localPath = decoratedPath.substring(dds);
            if (localPath.equals("..")) {
                localPath = "";
                parents++;
            }
            alignment = new ParentAlignment(parents);

        } else {
            localPath = decoratedPath;
            alignment = IPathAlignment.RELATIVE;
        }

        return new RelativePath(localPath, alignment);
    }

}
