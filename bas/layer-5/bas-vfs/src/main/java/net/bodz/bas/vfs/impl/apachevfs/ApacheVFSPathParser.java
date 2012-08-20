package net.bodz.bas.vfs.impl.apachevfs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.VFS;

import net.bodz.bas.vfs.path.AbstractGenericPathParser;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class ApacheVFSPathParser
        extends AbstractGenericPathParser {

    private final ApacheFileSystem fileSystem;
    private final Set<String> schemes;

    public ApacheVFSPathParser()
            throws FileSystemException {
        fileSystem = new ApacheFileSystem();
        Set<String> tmpset = new HashSet<String>();
        for (String scheme : VFS.getManager().getSchemes())
            tmpset.add(scheme);
        schemes = Collections.unmodifiableSet(tmpset);
    }

    @Override
    public boolean accepts(String protocol) {
        return schemes.contains(protocol);
    }

    @Override
    public IPath parse(String path)
            throws BadPathException {
        String uri = path;
        return fileSystem.parse(uri);
    }

}
