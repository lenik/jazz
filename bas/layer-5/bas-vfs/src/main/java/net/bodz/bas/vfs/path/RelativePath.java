package net.bodz.bas.vfs.path;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.align.IPathAlignment;
import net.bodz.bas.vfs.path.align.ParentAlignment;

public class RelativePath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    // int parents;
    IPathAlignment alignment;

    public RelativePath(String localPath) {
        this(localPath, 0);
    }

    public RelativePath(String localPath, int parents) {
        super(null /* dev */, localPath);
        this.alignment = new ParentAlignment(parents);
    }

    public RelativePath(String localPath, IPathAlignment alignment) {
        super(null/* dev */, localPath);

        if (alignment == null)
            throw new NullPointerException("alignment");

        this.alignment = alignment;
    }

    @Override
    public IFile resolve() {
        throw new IllegalUsageException("You can't resolve a relative path.");
    }

    @Override
    public IPathAlignment getAlignment() {
        return alignment;
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
