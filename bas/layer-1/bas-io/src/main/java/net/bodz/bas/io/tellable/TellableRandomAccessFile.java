package net.bodz.bas.io.tellable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import net.bodz.bas.sio.position.ITellable;

/**
 * @test {@link TellableRandomAccessFileTest}
 */
public class TellableRandomAccessFile
        extends RandomAccessFile
        implements ITellable {

    public TellableRandomAccessFile(File file, String mode)
            throws FileNotFoundException {
        super(file, mode);
    }

    public TellableRandomAccessFile(String name, String mode)
            throws FileNotFoundException {
        super(name, mode);
    }

    @Override
    public long tell() {
        try {
            return getFilePointer();
        } catch (IOException e) {
            return -1;
        }
    }

}
