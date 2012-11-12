package net.bodz.bas.vfs.impl.mem;

import net.bodz.bas.vfs.path.ManagedPath;

public class MemoryPath
        extends ManagedPath<MemoryPath> {

    private static final long serialVersionUID = 1L;

    public MemoryPath(MemoryPath parent, String baseName) {
        super(parent.getDevice(), baseName);
    }

    public MemoryPath(MemoryVfsDevice device, String baseName) {
        super(device, baseName);
    }

}
