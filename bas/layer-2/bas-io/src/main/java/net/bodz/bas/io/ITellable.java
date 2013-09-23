package net.bodz.bas.io;

import java.io.IOException;
import java.io.RandomAccessFile;

import net.bodz.bas.io.impl.TellableByteIn;
import net.bodz.bas.io.impl.TellableCharIn;

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

        public static final ITellable impl(IByteIn in) {
            return new TellableByteIn(in);
        }

        public static final ITellable impl(ICharIn in) {
            return new TellableCharIn(in);
        }

    }

}
