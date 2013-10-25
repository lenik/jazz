package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.FileFilter;

/**
 * Stop recurse into the sub-directory when the parent directory is filtered out by a
 * {@link PruneFileFilter}.
 */
public interface PruneFileFilter
        extends FileFilter {

    PruneFileFilter EXCLUDE_SVN_DIR = new ExcludeSvnDirFileFilter();

}

class ExcludeSvnDirFileFilter
        implements PruneFileFilter {
    @Override
    public boolean accept(File pathname) {
        if (".svn".equals(pathname.getName().toLowerCase()))
            return false;
        return true;
    }
}
