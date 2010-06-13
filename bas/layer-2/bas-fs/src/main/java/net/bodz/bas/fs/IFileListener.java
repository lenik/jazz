package net.bodz.bas.fs;

/**
 * @see org.apache.commons.vfs.FileListener
 */
public interface IFileListener {

    void fileCreated(FileChangeEvent event)
            throws Exception;

    void fileDeleted(FileChangeEvent event)
            throws Exception;

    void fileChanged(FileChangeEvent event)
            throws Exception;

}
