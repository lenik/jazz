package net.bodz.bas.vfs;

import net.bodz.bas.vfs.impl.fake.FakeVolume;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.align.IPathAlignment;
import net.bodz.bas.vfs.path.align.ParentAlignment;

public class RelativePath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    public RelativePath(String localPath, IPathAlignment alignment) {
        super(localPath, alignment);
    }

    @Override
    public IVolume getVolume() {
        return FakeVolume.getInstance();
    }

    @Override
    public IFile toFile() {
        String path = getAlignment().decorate(localPath);
        // XXX - Is `FakeVolume` suitable to use here?
        return FakeVolume.getInstance().resolveFile(path);
    }

    @Override
    public IPath getParentLayer() {
        return null;
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
