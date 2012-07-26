package net.bodz.bas.vfs.util;

import net.bodz.bas.vfs.IFile;

public interface IFileFilter {

    /**
     * Tests whether or not the specified file should be included.
     * 
     * @param file
     *            The file to be tested
     * @return <code>true</code> if and only if <code>file</code> should be included
     */
    boolean accept(IFile file);

}