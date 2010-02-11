package net.bodz.bas.fs.path;

public interface IPath {

    IPathAnchor getAnchor();

    int getAnchorPoints();

    /**
     * @throws IndexOutOfBoundsException
     */
    IPathAnchorPoint getAnchorPoint(int index);

    /**
     * Count of path entries.
     * Only 
     */
    int getEntryCount();

    /**
     * @throws IndexOutOfBoundsException
     */
    Object getEntry(int index);

    /**
     * Append by items.
     * 
     * @return non-<code>null</code> path.
     */
    IPath append(IPath append)
            throws PathException;

    /**
     * @return <code>null</code> If no more parent.
     */
    IPath getParent();

    /**
     * @return <code>null</code> If no more parent.
     */
    IPath getParent(int n);

    /**
     * @return non-<code>null</code> path.
     */
    IPath getRoot();

    int getLayerCount();

    /**
     * @throws IndexOutOfBoundsException
     */
    IPath getLayer(int layerIndex);

    /**
     * @return <code>null</code> If this is the root layer.
     */
    IPath getParentLayer();

    /**
     * @return non-<code>null</code> path.
     */
    IPath getRootLayer();

    /**
     * The anchor of given <code>path</code> may be used to anchor at this path.
     */
    IPath resolve(IPath path)
            throws PathException;

    String SEPARATOR = "/";
    char SEPARATOR_CHAR = '/';

    /**
     * Which anchor is used depends on whether the specified <code>path</code> is starts with `/'.
     */
    IPath resolve(String path)
            throws PathException;

    /**
     * @return non-<code>null</code> path.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    IPath reanchor(IPath absolute, IPathAnchor newAnchor);

    /**
     * @return non-<code>null</code> base name.
     */
    String getBaseName();

    /**
     * @return <code>null</code> if file doesn't have a name, or empty string if file doesn't have
     *         an extension.
     */
    String getExtension(boolean withDot, int maxWords);

    /**
     * @return non-<code>null</code> string.
     */
    String stripExtension();

}
