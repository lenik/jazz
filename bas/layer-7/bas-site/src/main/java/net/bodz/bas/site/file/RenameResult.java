package net.bodz.bas.site.file;

import java.io.File;

public class RenameResult {

    File oldFile;
    File newFile;
    String sha1;

    public RenameResult(File oldFile, File newFile, String sha1) {
        this.oldFile = oldFile;
        this.newFile = newFile;
        this.sha1 = sha1;
    }

}
