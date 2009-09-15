package net.bodz.bas.lop.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @test {@link TellableRandomAccessFileTest}
 */
public class TellableRandomAccessFile extends RandomAccessFile implements Tellable {

    public TellableRandomAccessFile(File file, String mode) throws FileNotFoundException {
        super(file, mode);
    }

    public TellableRandomAccessFile(String name, String mode) throws FileNotFoundException {
        super(name, mode);
    }

    @Override
    public long tell() throws IOException {
        return getFilePointer();
    }

}
