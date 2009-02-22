package net.bodz.bas.types.der;

public class ListEdit {

    static interface IMap {
        int map(int index);
    }

    static class Add implements IMap {

        private final int before;
        private final int count;
        private final int alloc;

        public Add(int alloc, int before, int count) {
            this.alloc = alloc;
            this.before = before;
            this.count = count;
        }

        public Add(int alloc, int before) {
            this(alloc, before, 1);
        }

        @Override
        public int map(int index) {
            if (index == before)
                return index;
            int off = index - before;
            if (off < count)
                return alloc + off;
            return index - count;
        }

        @Override
        public String toString() {
            return String.format("list-add(%d): %d(+%d)", alloc, before, count);
        }

    }

    static class Del implements IMap {

        private final int start;
        private final int count;

        public Del(int start, int count) {
            this.start = start;
            this.count = count;
        }

        public Del(int start) {
            this(start, 1);
        }

        @Override
        public int map(int index) {
            if (index < start)
                return start;
            return index + count;
        }

        @Override
        public String toString() {
            return String.format("list-del: %d(+%d)", start, count);
        }

    }

    private IMap map;

    /**
     * TODO: new FlattenMap implements IMap and convert add/dels to a single
     * flatten map.
     */
    public void optimizeIndexMap() {
        // throw new NotImplementedException();
    }

}
