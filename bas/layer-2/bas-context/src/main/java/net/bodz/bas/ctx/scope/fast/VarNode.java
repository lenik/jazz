package net.bodz.bas.ctx.scope.fast;

import java.util.Map.Entry;

import org.apache.commons.collections15.Transformer;

import net.bodz.bas.t.pojo.Pair;

class VarNode {

    VarNode prev;
    VarNode next;

    FastScopeFrame frame;
    VarNode prevVar;
    VarNode nextVar;

    Object data;

    public VarNode(Object data) {
        this.data = data;
    }

    public VarNode insert(Object data) {
        VarNode newPrev = frame.alloc(data);
        if (prev != null) {
            prev.next = newPrev;
            newPrev.prev = prev;
        }
        newPrev.next = this;
        this.prev = newPrev;
        return newPrev;
    }

    public void remove() {
        if (prev != null)
            prev.next = next;
        if (next != null)
            next.prev = prev;
    }

    public void detach() {
        prev = next = null;
        prevVar = nextVar = null;
    }

    public static class ToV<T>
            implements Transformer<VarNode, T> {

        @SuppressWarnings("unchecked")
        @Override
        public T transform(VarNode input) {
            return (T) input.data;
        }

    }

    public static class ToVEntry<K, T>
            implements Transformer<Entry<K, VarNode>, Entry<K, T>> {

        @SuppressWarnings("unchecked")
        @Override
        public Entry<K, T> transform(Entry<K, VarNode> input) {
            Pair<K, T> pair = new Pair<K, T>(input.getKey(), (T) input.getValue().data);
            return pair;
        }
    }

}
