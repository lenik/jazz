package net.bodz.bas.fs.path;


public interface IPathResolver {

    IPath resolve(String path)
            throws PathException;

}
