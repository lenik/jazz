package net.bodz.lily.entity;

import java.util.Collection;

public interface ILazyLoading {

    int SIZE_UNKNOWN = -1;
    long SIZEL_UNKNOWN = -1L;

    class SizeFn {

        public static int getSize(Collection<?> collection, int size) {
            if (collection == null)
                return size == SIZE_UNKNOWN ? 0 : size;
            if (!collection.isEmpty())
                return collection.size();
            if (size != SIZE_UNKNOWN)
                return size;
            return 0;
        }
    }

}
