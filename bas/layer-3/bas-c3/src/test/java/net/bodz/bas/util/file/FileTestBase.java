package net.bodz.bas.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

/**
 * XXX - create under test directory
 */
public class FileTestBase
        extends TestCase {

    protected File create(String path, String contents)
            throws IOException {
        File file = new File(path);
        FileOutputStream out = new FileOutputStream(file);
        try {
            out.write(contents.getBytes());
        } finally {
            out.close();
        }
        return file;
    }

    protected File file(String path) {
        return new File(path);
    }

}
