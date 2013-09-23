package net.bodz.bas.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Position tellable
 */
public interface ITellable {

    /**
     * Current position.
     * 
     * @return -1 if current position is unknown
     */
    long tell();

    class fn {

        public static final ITellable impl(final RandomAccessFile file) {
            return new ITellable() {
                @Override
                public long tell() {
                    try {
                        return file.getFilePointer();
                    } catch (IOException e) {
                        return -1;
                    }
                }
            };
        }

    }

}
