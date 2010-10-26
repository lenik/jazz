package net.bodz.bas.vfs;

public interface IFile
        extends IFsFileEntry, IFsFolderEntry {

    @Override
    IFile clone();

}
