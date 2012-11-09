package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;
import org.apache.commons.vfs.FileSystem;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.vfs.path.IPathSystem;

@IndexedType
public interface IVfsDriver {

    void configure(IPathSystem pathSystem);

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
