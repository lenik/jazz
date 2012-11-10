package net.bodz.bas.vfs;

import org.apache.commons.vfs.Capability;

public abstract class AbstractVfsDriver
        implements IVfsDriver {

    @Override
    public boolean hasCapability(Capability capability) {
        return false;
    }

    /**
     * @return <code>0</code>.
     */
    @Override
    public int getTimeAccuracy() {
        return 0;
    }

}
