package net.bodz.bas.files;

import java.io.File;
import java.io.InputStream;

/**
 * public static
 */
public interface FileMetaInfo {

    /**
     * @return possibility if the given file is of this file type
     */
    double typeOfPossibility(File file, InputStream in);

}
