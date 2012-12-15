package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;
import org.apache.commons.vfs.FileSystem;

// @IndexedType(obsoleted = true) // See IVfsDriverProvider
public interface IVfsDriver
        extends IVfsProtocolHandler {

    // IFileSystem getFileSystem();

    void configure(IFileSystem system);

    // /**
    // * @see FileProvider#getCapabilities()
    // */
    // Collection<Capability> getCapabilities();

    /**
     * @see FileSystem#hasCapability(org.apache.commons.vfs.Capability)
     */
    boolean hasCapability(Capability capability);

    /**
     * Returns the accuracy of file time attributes, like created-time, last-mod-time, and
     * access-time in ms.
     * 
     * @return ms of the least duration, 0 if unknown, or positive value.
     * @see org.apache.commons.vfs.FileSystem#getLastModTimeAccuracy()
     */
    int getTimeAccuracy();

}
