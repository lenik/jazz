package net.bodz.bas.t.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.java.util.PrefetchedIterator;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.util.Nullables;

public class TreePosition<N extends TreeNode<? extends N>>
        implements Iterable<N> {

    static class Dim<N extends TreeNode<? extends N>>
            implements Cloneable {

        public final N node;
        public final int index;

        public Dim(N node, int index) {
            this.node = node;
            this.index = index;
        }

        @Override
        protected Dim<N> clone() {
            return new Dim<N>(node, index);
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
            if (!Nullables.equals(node, a.node))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return node + "[" + index + "]";
        }

    }

    private List<Dim<N>> list;

    public TreePosition() {
        this(null, 0);
    }

    static <N extends TreeNode<? extends N>> List<Dim<N>> toDims(N parent, int... childIndices)
            throws IndexOutOfBoundsException {
        List<Dim<N>> list = new ArrayList<Dim<N>>(childIndices.length);
        for (int childIndex : childIndices) {
            Dim<N> dim = new Dim<N>(parent, childIndex);
            list.add(dim);
            List<? extends N> children = parent.getChildren();
            if (children == null)
                throw new IllegalArgumentException("Null children, can't go deeper");
            parent = children.get(childIndex);
        }
        return list;
    }

    public TreePosition(N parent, int... childIndices) {
        this(toDims(parent, childIndices));
    }

    public TreePosition(Dim<N> dim) {
        list = new ArrayList<Dim<N>>(1);
        list.add(dim);
    }

    public TreePosition(List<Dim<N>> dims) {
        list = new ArrayList<Dim<N>>(dims);
    }

    public TreePosition(TreePosition<N> ancestors, N parent, int... childIndices) {
        this(ancestors, toDims(parent, childIndices));
    }

    public TreePosition(TreePosition<N> ancestors, Dim<N> dim) {
        list = new ArrayList<Dim<N>>(ancestors.list);
        list.add(dim);
    }

    public TreePosition(TreePosition<N> ancestors, List<Dim<N>> dims) {
        list = new ArrayList<Dim<N>>(ancestors.list);
        list.addAll(dims);
    }

    static class Iter<N extends TreeNode<? extends N>>
            extends PrefetchedIterator<N> {

        private final ArrayStack<Dim<N>> stack;
        private final TreeNodePredicator<? super N> pred;
        private final List<TreePosition<N>> posBuf;

        public Iter(List<Dim<N>> dims, TreeNodePredicator<? super N> pred, List<TreePosition<N>> posBuf) {
            if (dims == null)
                throw new NullPointerException("dims");
            this.stack = new ArrayStack<Dim<N>>();
            for (Dim<N> dim : dims)
                stack.push(dim); // top -> dims[last]
            this.pred = pred;
            this.posBuf = posBuf;
        }

        @Override
        protected N fetch() {
            while (!stack.isEmpty()) {
                Dim<N> top = stack.top();
                N topNode = top.node;
                int topIndex = top.index;
                List<? extends N> children = topNode.getChildren();
                assert children != null : "term-node should not be iterated";
                int size = children.size();
                if (topIndex < size) { // there is current or more siblings
                    N current = children.get(topIndex);
                    List<? extends N> cc = current.getChildren();
                    if (cc != null && !cc.isEmpty()) { // current node have
                        // children
                        // recurse into children of the current node
                        stack.push(new Dim<N>(current, 0));
                        continue;
                    }
                }
                while (topIndex < size) { // iterate over the rest siblings
                    N nextChild = children.get(top.index);
                    if (pred != null && !pred.eval(nextChild)) {
                        // test failed, move to next sibling
                        stack.top(new Dim<N>(topNode, ++topIndex));
                        continue;
                    } else {
                        if (posBuf != null) { // copy on demand
                            TreePosition<N> posCopy = new TreePosition<N>(stack);
                            posBuf.add(posCopy);
                        }
                        // move to next sibling
                        stack.top(new Dim<N>(topNode, ++topIndex));
                        return nextChild;
                    }
                }
                stack.pop();
                if (!stack.isEmpty()) { // move to next parent
                    top = stack.top();
                    stack.top(new Dim<N>(top.node, top.index + 1));
                }
            }
            return end();
        }
    }

    /**
     * Iterate all nodes include or after the node at this position.
     */
    @Override
    public Iterator<N> iterator() {
        return iterator(null, null);
    }

    /**
     * Iterate all nodes filtered by pred, include or after the node at this position.
     * 
     * @param posRef
     *            save a copy of position of previous node just returned by {@link Iterator#next()}.
     */
    public Iterator<N> iterator(TreeNodePredicator<? super N> pred, List<TreePosition<N>> posBuf) {
        return new Iter<N>(list, pred, posBuf);
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
