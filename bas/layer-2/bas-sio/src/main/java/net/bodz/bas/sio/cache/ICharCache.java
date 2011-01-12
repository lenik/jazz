package net.bodz.bas.sio.cache;

public interface ICharCache {

    void setCacheSize(int cacheChars);

    int getCacheSize();

    /**
     * @return non-<code>null</code> char array size of {@link #getCacheSize()}.
     */
    char[] copyCache();

    /**
     * @return The actual characters have been copied.
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     */
    int copyCache(char[] buf);

    /**
     * @return The actual characters have been copied.
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If <code>off</code> or <code>len</code> if out of <code>buf</code> array.
     */
    int copyCache(char[] buf, int off, int len);

    /**
     * @return The actual characters have been copied.
     * @throws NullPointerException
     *             If <code>buf</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If <code>start</code> is out of cached size, or <code>off</code> or
     *             <code>len</code> if out of <code>buf</code> array.
     */
    int copyCache(int start, char[] buf, int off, int len);

    /**
     * Discard the cached contents.
     */
    void discardCache();

}
