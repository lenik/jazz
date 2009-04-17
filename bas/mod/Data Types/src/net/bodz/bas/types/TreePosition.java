package net.bodz.bas.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.lang.Pred1;
import net.bodz.bas.types.util.Objects;
import net.bodz.bas.types.util.PrefetchedIterator;

/**
 * @test {@link TreePositionTest}
 */
public class TreePosition<T extends TreeNode<? extends T>> implements
        Iterable<T> {

    static class Dim<T extends TreeNode<? extends T>> implements Cloneable {

        public final T   node;
        public final int index;

        public Dim(T node, int index) {
            this.node = node;
            this.index = index;
        }

        @Override
        protected Dim<T> clone() {
            return new Dim<T>(node, index);
        }

        @Override
        public int hashCode() {
            int hash = 0xa48e1e18;
            if (node != null)
                hash += node.hashCode();
            hash += index;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Dim<?>))
                return false;
            Dim<?> a = (Dim<?>) obj;
            if (index != a.index)
                return false;
            if (!Objects.equals(node, a.node))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return node + "[" + index + "]";
        }

    }

    private List<Dim<T>> list;

    public TreePosition() {
        this(null, 0);
    }

    static <T extends TreeNode<? extends T>> List<Dim<T>> toDims(T parent,
            int... childIndices) throws IndexOutOfBoundsException {
        List<Dim<T>> list = new ArrayList<Dim<T>>(childIndices.length);
        for (int childIndex : childIndices) {
            Dim<T> dim = new Dim<T>(parent, childIndex);
            list.add(dim);
            List<? extends T> children = parent.getChildren();
            // throws IndexOutOfBoundsException
            parent = children.get(childIndex);
        }
        return list;
    }

    public TreePosition(T parent, int... childIndices) {
        this(toDims(parent, childIndices));
    }

    public TreePosition(Dim<T> dim) {
        list = new ArrayList<Dim<T>>(1);
        list.add(dim);
    }

    public TreePosition(List<Dim<T>> dims) {
        list = new ArrayList<Dim<T>>(dims);
    }

    public TreePosition(TreePosition<T> ancestors, T parent,
            int... childIndices) {
        this(ancestors, toDims(parent, childIndices));
    }

    public TreePosition(TreePosition<T> ancestors, Dim<T> dim) {
        list = new ArrayList<Dim<T>>(ancestors.list);
        list.add(dim);
    }

    public TreePosition(TreePosition<T> ancestors, List<Dim<T>> dims) {
        list = new ArrayList<Dim<T>>(ancestors.list);
        list.addAll(dims);
    }

    static class Iter<T extends TreeNode<? extends T>> extends
            PrefetchedIterator<T> {

        private final ArrayStack<Dim<T>>    stack;
        private final Pred1<? super T>      pred;
        private final List<TreePosition<T>> posBuf;

        public Iter(List<Dim<T>> dims, Pred1<? super T> pred,
                List<TreePosition<T>> posBuf) {
            if (dims == null)
                throw new NullPointerException("dims");
            this.stack = new ArrayStack<Dim<T>>();
            for (Dim<T> dim : dims)
                stack.push(dim); // top -> dims[last]
            this.pred = pred;
            this.posBuf = posBuf;
        }

        @Override
        protected T fetch() {
            while (!stack.isEmpty()) {
                Dim<T> top = stack.top();
                T topNode = top.node;
                int topIndex = top.index;
                List<? extends T> children = topNode.getChildren();
                int size = children.size();
                if (topIndex < size) {
                    T current = children.get(topIndex);
                    List<? extends T> cc = current.getChildren();
                    if (cc != null && !cc.isEmpty()) {
                        stack.push(new Dim<T>(current, 0));
                        continue;
                    }
                }
                while (topIndex < size) {
                    T nextChild = children.get(top.index);
                    if (pred != null)
                        if (!pred.eval(nextChild)) {
                            stack.top(new Dim<T>(topNode, ++topIndex));
                            continue;
                        }
                    if (posBuf != null) {
                        TreePosition<T> posCopy = new TreePosition<T>(stack);
                        posBuf.add(posCopy);
                    }
                    stack.top(new Dim<T>(topNode, ++topIndex));
                    return nextChild;
                }
                stack.pop();
                if (!stack.isEmpty()) {
                    top = stack.top();
                    stack.top(new Dim<T>(top.node, top.index + 1));
                }
            }
            return end();
        }
    }

    /**
     * Iterate all nodes include or after the node at this position.
     */
    @Override
    public Iterator<T> iterator() {
        return iterator(null, null);
    }

    /**
     * Iterate all nodes filtered by pred, include or after the node at this
     * position.
     * 
     * @param posRef
     *            save a copy of position of previous node just returned by
     *            {@link Iterator#next()}.
     */
    public Iterator<T> iterator(Pred1<? super T> pred,
            List<TreePosition<T>> posBuf) {
        return new Iter<T>(list, pred, posBuf);
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
