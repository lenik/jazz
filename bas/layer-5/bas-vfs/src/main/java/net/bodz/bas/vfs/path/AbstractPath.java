package net.bodz.bas.vfs.path;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.align.IPathAlignment;
import net.bodz.bas.vfs.path.align.ParentAlignment;

public abstract class AbstractPath
        implements IPath {

    private static final long serialVersionUID = 1L;

    @Override
    public String getDeviceSpec() {
        return null;
    }

    @Override
    public String getDeviceSpecSeparator() {
        return SEPARATOR;
    }

    @Override
    public IFile resolve()
            throws FileResolveException {
        return VFS.resolve(this);
    }

    @Override
    public IPathAlignment getAlignment() {
        return IPathAlignment.ROOT_LAYER;
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

    @Override
    public IPath getParentLayer() {
        return null;
    }

    /**
     * Create a path with another local path.
     * 
     * @param localPath
     *            Non-<code>null</code> path with-in the same volume.
     * @return Non-<code>null</code> {@link IPath} instance.
     */
    protected abstract IPath createLocal(String localPath)
            throws BadPathException;

    @Override
    public IPath getRoot() {
        try {
            return createLocal("");
        } catch (BadPathException e) {
            throw new UnexpectedException("Bad root path", e);
        }
    }

    @Override
    public IPath getParent() {
        String localPath = getLocalPath();
        try {
            int last = localPath.lastIndexOf(SEPARATOR_CHAR);
            if (last == -1)
                return null;
            String parent = localPath.substring(0, last);
            return createLocal(parent);
        } catch (BadPathException e) {
            throw new UnexpectedException("Bad parent path", e);
        }
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
        return getExtension(false, 1);
    }

    @Override
    public String getExtension(boolean withDot) {
        return getExtension(withDot, 1);
    }

    @Override
    public String getExtension(boolean withDot, int maxWords) {
        if (maxWords < 1)
            throw new IllegalArgumentException("maxWords is less than 1.");

        String baseName = getBaseName();
        if (baseName == null)
            return null;

        int pos = baseName.length();
        while (maxWords-- > 0) {
            int dot = baseName.lastIndexOf('.', pos);
            if (dot == -1)
                break;
            pos = dot;
        }
        if (!withDot)
            pos++;
        if (pos < baseName.length())
            baseName.substring(pos);
        return "";
    }

    @Override
    public IPath join(String spec)
            throws BadPathException {
        if (spec == null)
            throw new NullPointerException("spec");
        String localPath1 = getLocalPath();

        String joinedLocalPath;
        if (spec.startsWith(SEPARATOR))
            joinedLocalPath = spec;
        else {
            if (localPath1.endsWith(SEPARATOR))
                joinedLocalPath = localPath1 + spec;
            else
                joinedLocalPath = localPath1 + SEPARATOR + spec;
        }

        return createLocal(joinedLocalPath);
    }

    @Override
    public IPath join(String... specs)
            throws BadPathException {
        IPath joined = this;
        for (String spec : specs)
            if (spec != null)
                joined = joined.join(spec);
        return joined;
    }

    @Override
    public IPath join(IPath spec)
            throws BadPathException {
        if (spec == null)
            throw new NullPointerException("spec");
        IPath alignedContext = spec.getAlignment().align(this);
        IPath joined = alignedContext.join(spec);
        return joined;
    }

    @Override
    public IPath join(IPath... specs)
            throws BadPathException {
        IPath joined = this;
        for (IPath spec : specs)
            if (spec != null)
                joined = joined.join(spec);
        return joined;
    }

    @Override
    public IPath getRelativePathTo(IPath basePath2) {
        IPathAlignment align1 = getAlignment();
        IPathAlignment align2 = basePath2.getAlignment();
        Class<?> alignType1 = align1.getClass();
        Class<?> alignType2 = align2.getClass();
        if (!alignType1.equals(alignType2))
            return null;

        if (alignType1.equals(ParentAlignment.class)) {
            ParentAlignment parentAlign1 = (ParentAlignment) align1;
            ParentAlignment parentAlign2 = (ParentAlignment) align2;
            int p1 = parentAlign1.getParents();
            int p2 = parentAlign2.getParents();
            if (p1 > p2) {
                int abs2 = -p2 + basePath2.getLocalEntryCount();
                int goUp = p1 - (-abs2);
                RelativePath path = new RelativePath(goUp, getLocalEntries());
                return path;
            } else {
                return null;
            }
        }

        String[] entries1 = getLocalEntries();
        String[] entries2 = basePath2.getLocalEntries();
        int minLen = Math.min(entries1.length, entries2.length);

        int gcd;
        for (gcd = 0; gcd < minLen; gcd++) {
            String entry1 = entries1[gcd];
            String entry2 = entries2[gcd];
            if (!entry1.equals(entry2))
                break;
        }

        int goUp2 = entries2.length - gcd;
        String[] goDown1v = Arrays.copyOfRange(entries1, gcd, entries1.length);

        RelativePath relativePath = new RelativePath(goUp2, goDown1v);
        return relativePath;
    }

    @Override
    public String getURLString() {
        PathFormat urlFormatOptions = new PathFormat();
        String url = format(urlFormatOptions);
        return url;
    }

    @Override
    public URL toURL()
            throws MalformedURLException {
        String url = getURLString();
        return new URL(url);
    }

    @Override
    public URI toURI()
            throws URISyntaxException {
        String url = getURLString();
        return new URI(url);
    }

    @Override
    public IPath canonicalize(PathCanonicalizeOptions canonicalizeOptions)
            throws FileResolveException {
        throw new NotImplementedException();
    }

    @Override
    public final String format(PathFormat pathFormat) {
        StringBuilder result = new StringBuilder(200);
        String protocol = getProtocol();
        if (protocol != null) {
            result.append(protocol);
            result.append(":");
        }
        format(result, pathFormat);
        return result.toString();
    }

    protected void format(StringBuilder result, PathFormat format) {
        String deviceSpec = getDeviceSpec();
        if (deviceSpec != null) {
            result.append(deviceSpec);
            result.append(getDeviceSpecSeparator());
        }
        String local = formatLocal(format);
        result.append(local);
    }

    /**
     * Format local path.
     * 
     * @return Non-<code>null</code> formatted local path with the specific format.
     */
    protected String formatLocal(PathFormat format) {
        return getLocalPath();
    }

    /**
     * Returns the path string in {@link PathFormats#DEFAULT default} format.
     */
    @Override
    public String toString() {
        return format(PathFormats.DEFAULT);
    }

}
