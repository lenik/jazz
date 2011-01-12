package net.bodz.bas.vfs.path;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.meta.codereview.PoorImpl;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.PathResolveException;
import net.bodz.bas.vfs.path.align.IPathAlignment;

public abstract class AbstractPath
        implements IPath {

    private static final long serialVersionUID = 1L;

    protected final String localPath;
    private final IPathAlignment alignment;

    public AbstractPath(String localPath) {
        this(localPath, IPathAlignment.ROOT);
    }

    public AbstractPath(String localPath, IPathAlignment alignment) {
        if (alignment == null)
            throw new NullPointerException("alignment");
        if (localPath == null)
            throw new NullPointerException("localPath");
        this.localPath = localPath;
        this.alignment = alignment;
    }

    @Override
    public IFile toFile()
            throws PathResolveException {
        return getVolume().resolveFile(localPath);
    }

    @Override
    public final IPathAlignment getAlignment() {
        return alignment;
    }

    @Override
    public final IPath getRootLayer() {
        IPath rootLayer = this;
        while (true) {
            IPath parentLayer = rootLayer.getParentLayer();
            if (parentLayer == null)
                break;
            rootLayer = parentLayer;
        }
        return rootLayer;
    }

    /**
     * @param localPath
     *            non-<code>null</code> path with-in the same volume.
     * @return non-<code>null</code> {@link IPath} instance.
     * @throws BadPathException
     */
    protected final IPath resolveLocal(String localPath)
            throws BadPathException {
        IVolume volume = getVolume();
        return volume.resolve(localPath);
    }

    @Override
    public IPath getParent() {
        try {
            int last = localPath.lastIndexOf(SEPARATOR_CHAR);
            if (last == -1)
                return null;
            String parent = localPath.substring(0, last);
            return resolveLocal(parent);
        } catch (BadPathException e) {
            throw new UnexpectedException("Bad parent path", e);
        }
    }

    @PoorImpl
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
        try {
            return resolveLocal("");
        } catch (BadPathException e) {
            throw new UnexpectedException("Bad root path", e);
        }
    }

    @Override
    public IPath join(String path)
            throws BadPathException {
        if (path == null)
            throw new NullPointerException("path");

        String joinedLocalPath;
        if (path.startsWith(SEPARATOR))
            joinedLocalPath = path;
        else {
            if (localPath.endsWith(SEPARATOR))
                joinedLocalPath = localPath + path;
            else
                joinedLocalPath = localPath + SEPARATOR + path;
        }

        return resolveLocal(joinedLocalPath);
    }

    @Override
    public IPath join(IPath path)
            throws BadPathException {
        if (path == null)
            throw new NullPointerException("path");

        IPath alignedPath = path.getAlignment().align(this);
        return alignedPath;
    }

    /**
     * TODO ..
     */
    @Override
    public IPath getRelativePath(IPath basePath) {
        throw new NotImplementedException();
    }

    @Override
    public final String getLocalPath() {
        return localPath;
    }

    @Override
    public String[] getLocalEntries() {
        return localPath.split(SEPARATOR);
    }

    @Override
    public int getLocalEntryCount() {
        return getLocalEntries().length;
    }

    @Override
    public String getLocalEntry(int index) {
        String[] entries = getLocalEntries();
        return entries[index];
    }

    @Override
    public String getDirName() {
        String path = getLocalPath();
        assert path != null;
        int slash = path.lastIndexOf(SEPARATOR_CHAR);
        if (slash == -1)
            return null;
        String dirName = path.substring(0, slash - 1);
        return dirName;
    }

    @Override
    public String getBaseName() {
        int n = getLocalEntryCount();
        assert n > 0;
        return getLocalEntry(n - 1);
    }

    @Override
    public String getStrippedName() {
        String name = getBaseName();
        int dot = name.lastIndexOf('.');
        if (dot != -1)
            name = name.substring(0, dot - 1);
        return name;
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
    public String getURL() {
        PathFormat urlFormatOptions = null;
        String url = format(urlFormatOptions);
        return url;
    }

    @Override
    public URL toURL()
            throws MalformedURLException {
        String url = getURL();
        return new URL(url);
    }

    @Override
    public URI toURI()
            throws URISyntaxException {
        String url = getURL();
        return new URI(url);
    }

    @Override
    public IPath canonicalize(PathCanonicalizeOptions canonicalizeOptions)
            throws PathResolveException {
        throw new NotImplementedException();
    }

    @Override
    public String format(PathFormat pathFormat) {
        return getVolume().format(this, pathFormat);
    }

    /**
     * Returns the path string in {@link PathFormats#DEFAULT default} format.
     */
    @Override
    public String toString() {
        return format(PathFormats.DEFAULT);
    }

}
