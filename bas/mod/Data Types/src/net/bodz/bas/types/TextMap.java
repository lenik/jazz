package net.bodz.bas.types;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public interface TextMap<T> extends Map<String, T> {

    public class HashTextMap<T> extends HashMap<String, T> implements
            TextMap<T> {

        private static final long serialVersionUID = 6592220579601421664L;

        public HashTextMap() {
            super();
        }

        public HashTextMap(int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
        }

        public HashTextMap(int initialCapacity) {
            super(initialCapacity);
        }

        public HashTextMap(Map<? extends String, ? extends T> m) {
            super(m);
        }

    }

    public class TreeTextMap<T> extends TreeMap<String, T> implements
            TextMap<T> {

        private static final long serialVersionUID = -6119969398907948801L;

        public TreeTextMap() {
            super();
        }

        public TreeTextMap(Comparator<? super String> comparator) {
            super(comparator);
        }

        public TreeTextMap(Map<? extends String, ? extends T> m) {
            super(m);
        }

        public TreeTextMap(SortedMap<String, ? extends T> m) {
            super(m);
        }

    }

}
