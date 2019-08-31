package net.bodz.bas.site.file;

import java.io.File;

/**
 * @see UploadFn#submitFiles(Object, java.util.List, String, net.bodz.lily.entity.ILazyId,
 *      IFileNameListener)
 */
public interface IFileNameListener {

    void onFileNameChange(File oldName, File newName);

    IFileNameListener NOP = new IFileNameListener() {
        @Override
        public void onFileNameChange(File oldName, File newName) {
        }
    };

}
