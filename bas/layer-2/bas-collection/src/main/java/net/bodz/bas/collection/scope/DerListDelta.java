package net.bodz.bas.collection.scope;

import net.bodz.bas.exceptions.NotImplementedException;

public class DerListDelta {

    static interface IMap {
        int map(int v);
    }

    static class Ident implements IMap {

        @Override
        public int map(int v) {
            return v;
        }

        @Override
        public String toString() {
            return "ident"; 
        }

    }

    static final Ident IDENT = new Ident();

    static class Add implements IMap {

        private final IMap prev;
        private final int vBefore;
        private final int n;
        private final int qNext;

        public Add(IMap prev, int qNext, int vBefore, int n) {
            this.prev = prev;
            this.qNext = qNext;
            this.vBefore = vBefore;
            this.n = n;
        }

        public Add(IMap prev, int qNext, int before) {
            this(prev, qNext, before, 1);
        }

        @Override
        public int map(int v) {
            if (v < vBefore)
                return prev.map(v);
            int off = v - vBefore;
            if (off < n)
                return qNext + off;
            return prev.map(v - n);
        }

        @Override
        public String toString() {
            return String.format("list-add(%d): %d(+%d)", qNext, vBefore, n); 
        }

    }

    static class Remove implements IMap {

        private final IMap prev;
        private final int vStart;
        private final int n;

        public Remove(IMap prev, int v, int n) {
            this.prev = prev;
            this.vStart = v;
            this.n = n;
        }

        public Remove(IMap prev, int v) {
            this(prev, v, 1);
        }

        @Override
        public int map(int v) {
            if (v < vStart)
                return prev.map(v);
            return prev.map(v + n);
        }

        @Override
        public String toString() {
            return String.format("list-remove: %d(+%d)", vStart, n); 
        }

    }

    private IMap map = IDENT;

    // private

    /**
     * TODO: new FlattenMap implements IMap and convert add/dels to a single flatten map.
     */
    public void optimizeIndexMap() {
        // throw new NotImplementedException();
    }

    /**
     * 好比延期修改了先决条件，在原型中增加的部分则在delta中去除。
     */
    void addOrig(int before, int count) {
        // maps.add(new OrigAdd(...??))
        throw new NotImplementedException();
    }

    /**
     * 好比延期修改了先决条件，在原型中除去的部分则在delta中增加。
     */
    void removeOrig(int start, int count) {
        // maps.add(new OrigDel(...??))
        throw new NotImplementedException();
    }

    public void add(int qNext, int vBefore, int count) {
        map = new Add(map, qNext, vBefore, count);
    }

    public void add(int qNext, int vBefore) {
        map = new Add(map, qNext, vBefore);
    }

    public void remove(int vStart, int count) {
        map = new Remove(map, vStart, count);
    }

    public void remove(int vStart) {
        map = new Remove(map, vStart);
    }

    /**
     * This is NOT the same as remove-all.
     */
    public void reset() {
        map = IDENT;
    }

    public int map(int v) {
        return map.map(v);
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
