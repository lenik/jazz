package net.bodz.bas.vfs;

public interface IFsBlobAttributes
        extends IFsObjectAttributes {

    boolean isExecutable();

    /**
     * Return whether this file is random seekable.
     * 
     * @return <code>true</code> If this file is ramdom seekable.
     */
    boolean isSeekable();

}
