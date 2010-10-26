package net.bodz.bas.vfs.path;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.align.IPathAlignment;

public abstract class AbstractPath
        implements IPath {

    private final IVolume volume;
    private final IPathAlignment align;

    private final IPath parentLayer;

    public AbstractPath(IVolume fileSystem, IPathAlignment alignment, IPath parentLayer) {
        if (alignment == null)
            throw new NullPointerException("alignment");
        this.volume = fileSystem;
        this.align = alignment;
        this.parentLayer = parentLayer;
    }

    @Override
    public IPathAlignment getAlignment() {
        return align;
    }

    @Override
    public IVolume getVolume() {
        return volume;
    }

    @Override
    public IPath getParentLayer() {
        return parentLayer;
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

    @Override
    public String getURL() {
        IVolume volume = getVolume();
        throw new NotImplementedException();
    }

    @Override
    public URI toURI()
            throws URISyntaxException {
        String url = getURL();
        return new URI(url);
    }

    @Override
    public URL toURL()
            throws MalformedURLException {
        String url = getURL();
        return new URL(url);
    }

    @Override
    public IFile toFile() {
        return volume.resolveFile(this);
    }

    @Override
    public String getDirName() {
        String path = getPath();
        assert path != null;
        int slash = path.lastIndexOf(SEPARATOR_CHAR);
        if (slash == -1)
            return null;
        String dirName = path.substring(0, slash - 1);
        return dirName;
    }

    @Override
    public String getBaseName() {
        int n = getEntryCount();
        assert n > 0;
        return getEntry(n - 1);
    }

    @Override
    public String getExtension() {
        String name = getBaseName();
        int dot = name.lastIndexOf('.');
        if (dot == -1)
            return null;
        return name.substring(dot + 1);
    }

    @Override
    public String getExtensionWithDot() {
        String name = getBaseName();
        int dot = name.lastIndexOf('.');
        if (dot == -1)
            return null;
        return name.substring(dot);
    }

    @Override
    public String getStrippedName() {
        String name = getBaseName();
        int dot = name.lastIndexOf('.');
        if (dot != -1)
            name = name.substring(0, dot - 1);
        return name;
    }

}
