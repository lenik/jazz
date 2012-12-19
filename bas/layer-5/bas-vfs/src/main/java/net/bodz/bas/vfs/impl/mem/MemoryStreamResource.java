package net.bodz.bas.vfs.impl.mem;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.ProxyStreamResource;
import net.bodz.bas.vfs.util.Inode;

class MemoryStreamResource
        extends ProxyStreamResource {

    private final MemoryFile file;

    public MemoryStreamResource(MemoryFile file) {
        super(null);
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public MemoryStreamResource clone() {
        MemoryStreamResource o = new MemoryStreamResource(file);
        o.setCharset(getCharset());
        o.setAppendMode(isAppendMode());
        return o;
    }

    @Override
    public IStreamResource getWrapped()
            throws IOException {
        Inode inode = file.getInode();
        if (inode == null)
            // 1. for newOut*, the node is already created before getWrapped()
            // 2. for newIn*, null node means non-exist parent dir.
            throw new FileNotFoundException("File isn't existed: " + file.getPath());

        IStreamResource data = (IStreamResource) inode.getData();
        if (data == null)
            // 1. for newOut*, the resource is already created before getWrapped()
            // 2. for newIn*, null resource means it's a dir-node or the file isn't created.
            throw new FileNotFoundException("Not a file: " + file.getPath());

        return data;
    }

    @Override
    protected void beforeOpenOutput(boolean append)
            throws IOException {
        super.beforeOpenOutput(append);
        file.touch(false);
    }

}
