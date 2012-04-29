package net.bodz.bas.repr.resource;

import net.bodz.bas.vfs.FileResolveException;


public class FjvfsResourceFactory
        extends ResourceFactory {

    @Override
    public String getType() {
        return ResourceTypes.RT_FJVFS;
    }

    @Override
    public IResource resolve(String path) {
        String pathDecoded = path;
        try {
            return new FjvfsResource(pathDecoded);
        } catch (FileResolveException e) {
            return null;
        }
    }

}
