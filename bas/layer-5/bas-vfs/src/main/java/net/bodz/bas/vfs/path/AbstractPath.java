package net.bodz.bas.vfs.path;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.FileResolveOptions;
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
    public final IFile toFile()
            throws FileResolveException {
        return toFile(FileResolveOptions.DEFAULT);
    }

    @Override
    public IFile toFile(FileResolveOptions options)
            throws FileResolveException {
        return VFS.resolve(this, options);
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
    @NotNull
    protected abstract IPath createLocal(String localPath)
            throws BadPathException;

    /**
     * Create a path with another local path.
     *
     * @param localEntries
     *            Non-<code>null</code> path entries with-in the same volume.
     * @return Non-<code>null</code> {@link IPath} instance.
     */
    @NotNull
    protected abstract IPath createLocal(String[] localEntries, boolean entered);

    /**
     * Create a path with another local path.
     *
     * @param localEntries
     *            Non-<code>null</code> path entries with-in the same volume.
     * @return Non-<code>null</code> {@link IPath} instance.
     */
    @NotNull
    protected final IPath createLocal(String[] entries, int start, int end, boolean entered)
            throws BadPathException {
        if (start != 0 || end != entries.length)
            entries = Arrays.copyOfRange(entries, start, end);
        return createLocal(entries, entered);
    }

    @Override
    public IPath getRoot() {
        try {
            return createLocal(SEPARATOR);
        } catch (BadPathException e) {
            throw new UnexpectedException("Bad root path", e);
        }
    }

    @Override
    public IPath getParent() {
        String localPath = joinEntries();
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
    public String getDirName() {
        String path = joinEntries();
        if (isEntered())
            return path;
        int lastSlash = path.lastIndexOf(SEPARATOR_CHAR);
        if (lastSlash == -1)
            return null;
        String dirName = path.substring(0, lastSlash - 1);
        return dirName;
    }

    @Override
    public String getBaseName() {
        if (isEntered())
            return null;
        int n = getEntryCount();
        if (n == 0)
            return "";
        else
            return getEntry(n - 1);
    }

    @Override
    public String getName() {
        if (isEntered())
            return null;
        String baseName = getBaseName();
        int dot = baseName.lastIndexOf('.');
        if (dot == -1)
            return baseName;
        else
            return baseName.substring(0, dot - 1);
    }

    @Override
    public String getExtension(int maxWords) {
        return _extension(false, maxWords);
    }

    @NotNull
    @Override
    public String getDotExtension(int maxWords) {
        String extension = _extension(true, maxWords);
        return extension == null ? "" : extension;
    }

    private String _extension(boolean withDot, int maxWords) {
        if (maxWords < 1)
            throw new IllegalArgumentException("maxWords is less than 1.");

        String baseName = getBaseName();
        if (baseName == null)
            return null;

        int len = baseName.length();
        int pos = len;
        while (maxWords-- > 0) {
            int dot = baseName.lastIndexOf('.', pos);
            if (dot == -1)
                break;
            pos = dot;
        }
        if (pos >= len)
            return withDot ? "" : null;

        return baseName.substring(withDot ? pos : (pos + 1));
    }

    @NotNull
    @Override
    public IPath enter() {
        if (isEntered())
            return this;
        else
            return createLocal(getEntryArray(), true);
    }

    @NotNull
    @Override
    public IPath escape() {
        if (!isEntered())
            return this;
        else
            return createLocal(getEntryArray(), false);
    }

    @NotNull
    @Override
    public IPath normalize() {
        String[] newEntries = new String[getEntryCount()];
        int out = 0;
        for (String entry : getEntryList()) {
            switch (entry) {
                case ".":
                    continue;
                case "..":
                    if (out > 0) {
                        out--;
                        continue;
                    }
            }
            newEntries[out++] = entry;
        }
        return createLocal(newEntries, 0, out, isEntered());
    }

    @Override
    public IPath resolve(@NotNull String other)
            throws BadPathException {
        String localPath = joinEntries();

        String joinedLocalPath;
        if (other.startsWith(SEPARATOR))
            joinedLocalPath = other;
        else {
            String localDir = localPath;
            if (!isEntered())
                localDir = StringPart.beforeLast(localDir, SEPARATOR);

            if (localDir == null)
                joinedLocalPath = other;
            else
                joinedLocalPath = localDir + SEPARATOR + other;
        }

        return createLocal(joinedLocalPath);
    }

    @Override
    public IPath resolve(@NotNull String... others)
            throws BadPathException {
        IPath joined = this;
        for (String spec : others)
            if (spec != null)
                joined = joined.resolve(spec);
        return joined;
    }

    @Override
    public IPath resolve(@NotNull IPath other)
            throws BadPathException {
        IPath alignedContext = other.getAlignment().align(this);
        IPath joined = alignedContext.resolve(other);
        return joined;
    }

    @Override
    public IPath resolve(@NotNull IPath... others)
            throws BadPathException {
        IPath joined = this;
        for (IPath spec : others)
            if (spec != null)
                joined = joined.resolve(spec);
        return joined;
    }

    @Override
    public IPath getRelativePathTo(@NotNull IPath basePath2) {
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
                int abs2 = -p2 + basePath2.getEntryCount();
                int goUp = p1 - (-abs2);
                RelativePath path = new RelativePath(goUp, getEntryArray(), isEntered());
                return path;
            } else {
                return null;
            }
        }

        String[] entries1 = getEntryArray();
        String[] entries2 = basePath2.getEntryArray();
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

        RelativePath relativePath = new RelativePath(goUp2, goDown1v, isEntered());
        return relativePath;
    }

    @NotNull
    @Override
    public String getURLString() {
        PathFormat urlFormatOptions = new PathFormat();
        String url = format(urlFormatOptions);
        return url;
    }

    @NotNull
    @Override
    public URI toURI()
            throws URISyntaxException {
        String url = getURLString();
        return URI.create(url);
    }

    @NotNull
    @Override
    public final String format(PathFormat pathFormat) {
        StringBuilder result = new StringBuilder(200);
        String protocol = getProtocol();
        if (protocol != null) {
            result.append(protocol);
            result.append(":");
        }
        format(result, pathFormat);
        if (isEntered())
            result.append(SEPARATOR);
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
        return joinEntries();
    }

    /**
     * Returns the path string in {@link PathFormats#STATIC default} format.
     */
    @Override
    public String toString() {
        return format(PathFormats.REPR);
    }

}
