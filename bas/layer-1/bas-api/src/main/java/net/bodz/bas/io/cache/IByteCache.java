package net.bodz.bas.io.cache;

public interface IByteCache {

    void setCacheSize(int cacheBytes);

    int getCacheSize();

    /**
     * @return non-<code>null</code> byte array size of {@link #getCacheSize()}.
     */
    byte[] copyCache();

    /**
     * @return The actual characters have been copied.
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     */
    int copyCache(byte[] buf);

    /**
     * @return The actual characters have been copied.
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If <code>off</code> or <code>len</code> if out of <code>buf</code> array.
     */
    int copyCache(byte[] buf, int off, int len);

    /**
     * @return The actual characters have been copied.
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If <code>start</code> is out of cached size, or <code>off</code> or
     *             <code>len</code> if out of <code>buf</code> array.
     */
    int copyCache(int start, byte[] buf, int off, int len);

    /**
     * Discard the cached contents.
     */
    void discardCache();

}
