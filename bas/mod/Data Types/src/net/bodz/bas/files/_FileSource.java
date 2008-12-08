package net.bodz.bas.files;


public abstract class _FileSource<T> implements FileSource<T> {

    protected final RecordBuilder<T> builder;

    protected final Object           in;
    protected final Object           charset;

    /**
     * @param in
     *            can be File, URL
     */
    protected _FileSource(Object in, Object charset) {
        this.in = in;
        this.charset = charset;
        builder = getRecordBuilder();
        if (builder == null)
            throw new NullPointerException("builder");
    }

    protected _FileSource(Object in) {
        this(in, null);
    }

    protected abstract RecordBuilder<T> getRecordBuilder();

}
