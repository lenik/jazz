package net.bodz.bas.util.primitive;

import java.util.Collection;

public class ByteCollection {

    public static byte[] toArray(Collection<Byte> collection) {
        int size = collection.size();
        byte[] array = new byte[size];
        int i = 0;
        for (Byte boxed : collection)
            array[i++] = boxed;
        return array;
    }

}
