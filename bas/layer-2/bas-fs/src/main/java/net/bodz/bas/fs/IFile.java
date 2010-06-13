package net.bodz.bas.fs;

public interface IFile
        extends IFsFileEntry, IFsFolderEntry {

    @Override
    IFile clone();

}
