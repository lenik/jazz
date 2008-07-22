package net.bodz.mda.parsers.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FilePositionRandomAccessFile extends RandomAccessFile implements
        FilePosition {

    public FilePositionRandomAccessFile(File file, String mode)
            throws FileNotFoundException {
        super(file, mode);
    }

    public FilePositionRandomAccessFile(String name, String mode)
            throws FileNotFoundException {
        super(name, mode);
    }

    @Override
    public long tell() throws IOException {
        return getFilePointer();
    }

}
