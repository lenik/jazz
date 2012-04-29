package net.bodz.bas.repr.resource;

public class FileResourceFactory
        extends ResourceFactory {

    @Override
    public String getType() {
        return ResourceTypes.RT_FILE;
    }

    @Override
    public FileResource resolve(String path) {
        return new FileResource(path);
    }

}
