package net.bodz.lily.model.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;

public abstract class CoNode<Self extends CoNode<Self, Id>, Id>
        extends CoEntity<Id> {

    private static final long serialVersionUID = 1L;

    private Self parent;
    private List<Self> children = new ArrayList<Self>();

    public CoNode() {
        this(null);
    }

    public CoNode(Self parent) {
        this.parent = parent;
        if (parent != null)
            parent.addChild(self());
    }

    @SuppressWarnings("unchecked")
    private Self self() {
        return (Self) this;
    }

    /**
     * @label Parent
     * @label.zh 父结点
     * @placeholder 选择一个父结点…
     */
    @OfGroup(StdGroup.Graph.class)
    public Self getParent() {
        return parent;
    }

    public void setParent(Self parent) {
        if (parent != null)
            checkNode(false, parent);
        this.parent = parent;
    }

    /**
     * @label Children
     * @label.zh 子结点
     */
    @OfGroup(StdGroup.Graph.class)
    @DetailLevel(DetailLevel.EXTEND)
    public List<Self> getChildren() {
        return children;
    }

    public void setChildren(List<Self> children) {
        if (children == null)
            throw new NullPointerException("children");
        this.children = children;
    }

    protected boolean isChildUnique() {
        return false;
    }

    public boolean addChild(Self child) {
        if (child == null)
            throw new NullPointerException("child");

        checkNode(true, child);

        if (isChildUnique())
            if (children.contains(child))
                return false;

        children.add(child);
        child.setParent(self());
        return true;
    }

    public boolean removeChild(Self child) {
        if (child == null)
            throw new NullPointerException("child");

        checkNode(true, child);

        if (!children.remove(child))
            return false;

        child.setParent(null);
        return true;
    }

    /**
     * @label Root
     * @label.zh 根结点
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public boolean isRoot() {
        return parent != null;
    }

    int indexOf(Self child) {
        return children.indexOf(child);
    }

    /**
     * @label Index
     * @label.zh 序号
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public int getIndex() {
        if (parent == null)
            return 0;
        Self self = self();
        return parent.indexOf(self);
    }

    public int size() {
        return children.size();
    }

    /**
     * @label Depth
     * @label.zh 深度
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public int getDepth() {
        int safeDepth = getSafeDepth();
        int depth = 0;
        Self node = self();
        while (node != null) {
            node = node.getParent();
            depth++;
            if (depth >= safeDepth)
                throw new IllegalUsageException(
                        "Exceeds the max depth of a tree, maybe there's dead loop? Last node = " + node);
        }
        return depth;
    }

    void setDepth(int depth) {
    }

    @OfGroup(StdGroup.Graph.class)
    @DetailLevel(DetailLevel.HIDDEN)
    protected int getSafeDepth() {
        return 1000;
    }

    /**
     * @label First
     * @label.zh 首位
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public boolean isFirst() {
        if (parent == null)
            return true;
        else
            return getIndex() == 0;
    }

    /**
     * @label Last
     * @label.zh 末位
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public boolean isLast() {
        if (parent == null)
            return true;
        else
            return getIndex() == parent.size() - 1;
    }

    /**
     * @label Node Chain
     * @label.zh 结点链
     */
    @OfGroup(StdGroup.Graph.class)
    public CoNodeChain<Self> getChain() {
        CoNodeChain<Self> chain = new CoNodeChain<Self>();
        Self node = self();
        while (node != null) {
            chain.add(node);
            node = node.getParent();
        }
        Collections.reverse(chain);
        return chain;
    }

    /**
     * @label Graph Prefix
     * @label.zh 图前缀
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public String getGraphPrefix() {
        if (parent == null)
            return "";

        StringBuilder buf = new StringBuilder();

        if (!isLast())
            buf.append(" -| "); // _|-_
        else
            buf.append(" -` "); // _`-_

        Self node = parent;
        while (node != null) {
            if (!node.isLast())
                buf.append("  | "); // _|__
            else
                buf.append("    "); // ____
            node = node.getParent();
        }

        buf.reverse();
        return buf.toString();
    }

    /**
     * @label Node Label
     * @label.zh 结点标签
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public String getNodeLabel() {
        // return naturalId().toString();
        String str = getCodeName();
        if (str == null)
            str = "(no code)";

        String label = getLabel();
        if (label != null)
            str += " 【" + label + "】";

        return str;
    }

    /**
     * Check if the given node may be added to the tree.
     * 
     * @param child
     *            <code>true</code> for child node, <code>false</code> for parent node.
     * @param node
     *            Non-<code>null</code> node to be checked.
     * @throws IllegalArgumentException
     *             If the node type is illegal.
     */
    protected void checkNode(boolean child, Self node) {
        Class<?> selfType = getClass();
        if (!selfType.isInstance(node))
            throw new IllegalArgumentException("Inconsistent node type: tree=" + selfType + ", node=" + node.getClass());
    }

    /**
     * TODO conformadate to PrincipalDiag#checkDeadLoop.
     * 
     * @param order
     *            Max number of nodes in the graph, this enables fast check.
     */
    public boolean checkLoopFast(int order) {
        if (order < 1)
            throw new IllegalArgumentException("Order should be positive integer: " + order);
        Self node = self();
        for (int i = 0; i < order; i++) {

            Self _node = node.getParent();
            node = _node;

            if (node == null)
                return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    protected static <S, T> List<T> cast(List<S> sv) {
        List<T> tv = new ArrayList<T>();
        for (S s : sv) {
            T t = (T) s;
            tv.add(t);
        }
        return tv;
    }

    void dump(ICharOut out)
            throws IOException {
        out.write(getGraphPrefix());
        out.write(getNodeLabel());
        out.write('\n');

        for (Self child : children)
            child.dump(out);
    }

    public void dump()
            throws IOException {
        dump(Stdio.cout);
    }

}
