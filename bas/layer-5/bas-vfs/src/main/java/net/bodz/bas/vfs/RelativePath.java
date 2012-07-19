package net.bodz.bas.vfs;

import net.bodz.bas.vfs.impl.fake.FakeFileSystem;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.DefaultPath;
import net.bodz.bas.vfs.path.align.IPathAlignment;
import net.bodz.bas.vfs.path.align.ParentAlignment;

public class RelativePath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    public RelativePath(String localPath, IPathAlignment alignment) {
        super(null, localPath, alignment);
    }

    @Override
    public IFileSystem getFileSystem() {
        return FakeFileSystem.getInstance();
    }

    @Override
    public IFile toFile()
            throws BadPathException {
        String path = getAlignment().decorate(localPath);
        // XXX - Is `FakeVolume` suitable to use here?
        return FakeFileSystem.getInstance().resolve(path);
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
