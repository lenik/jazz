package net.bodz.bas.io.util;

import java.util.zip.Checksum;

public class Checksums {

    public static abstract class _Checksum implements Checksum, Cloneable {

        public void update(byte[] bytes, int off, int len) {
            while (len-- > 0) {
                int b = (bytes[off++]) & 0xff;
                update(b);
            }
        }

        public void update(byte[] bytes) {
            update(bytes, 0, bytes.length);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + ":" + getValue(); 
        }

    }

    public interface IKey {

        int getKey();

        void setKey(int key);

    }

}
