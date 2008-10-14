package net.bodz.bas.types;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public interface TypeMap<T> extends Map<Class<?>, T> {

    public class HashTypeMap<T> extends HashMap<Class<?>, T> implements
            TypeMap<T> {

        private static final long serialVersionUID = 6588832879702158986L;

        public HashTypeMap() {
            super();
        }

        public HashTypeMap(int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
        }

        public HashTypeMap(int initialCapacity) {
            super(initialCapacity);
        }

        public HashTypeMap(Map<? extends Class<?>, ? extends T> m) {
            super(m);
        }

    }

    public class TreeTypeMap<T> extends TreeMap<Class<?>, T> implements
            TypeMap<T> {

        private static final long serialVersionUID = -4910446627162409067L;

        public TreeTypeMap() {
            super();
        }

        public TreeTypeMap(Comparator<? super Class<?>> comparator) {
            super(comparator);
        }

        public TreeTypeMap(Map<? extends Class<?>, ? extends T> m) {
            super(m);
        }

        public TreeTypeMap(SortedMap<Class<?>, ? extends T> m) {
            super(m);
        }

    }

}
