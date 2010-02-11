package net.bodz.bas.fs.path;

public abstract class AbstractPath
        implements IPath {

    @Override
    public int getAnchorPoints() {
        return 0;
    }

    @Override
    public IPathAnchorPoint getAnchorPoint(int index) {
        throw new IndexOutOfBoundsException(String.valueOf(index));
    }

    @Override
    public IPath getParent(int n) {
        IPath np = this;
        while (n > 0) {
            IPath p = np.getParent();
            if (p == null)
                return null;
            np = p;
            n--;
        }
        return np;
    }

    @Override
    public IPath getRoot() {
        IPath root = this;
        while (true) {
            IPath parent = root.getParent();
            if (parent == null)
                break;
            root = parent;
        }
        return root;
    }

    @Override
    public IPath getRootLayer() {
        IPath rootLayer = this;
        while (true) {
            IPath parentLayer = rootLayer.getParentLayer();
            if (parentLayer == null)
                break;
            rootLayer = parentLayer;
        }
        return rootLayer;
    }

}
