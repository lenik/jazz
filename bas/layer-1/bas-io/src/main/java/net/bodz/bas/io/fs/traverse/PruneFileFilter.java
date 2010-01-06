package net.bodz.bas.io.fs.traverse;

import java.io.FileFilter;

/**
 * Stop recurse into the sub-directory when the parent directory is filtered out by a
 * {@link PruneFileFilter}.
 */
public interface PruneFileFilter extends FileFilter {

}
