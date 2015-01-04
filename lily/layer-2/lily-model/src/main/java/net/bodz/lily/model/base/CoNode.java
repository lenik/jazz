package net.bodz.lily.model.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.meta.cache.Derived;

public abstract class CoNode<self_t extends CoNode<self_t>>
        extends CoEntity {

    private static final long serialVersionUID = 1L;

    private self_t parent;
    private List<self_t> children = new ArrayList<self_t>();

    public CoNode() {
        this(null);
    }

    public CoNode(self_t parent) {
        this.parent = parent;
        if (parent != null)
            parent.addChild(self());
    }

    @SuppressWarnings("unchecked")
    private self_t self() {
        return (self_t) this;
    }

    /**
     * @label Parent
     * @label.zh 父对象
     */
    public self_t getParent() {
        return parent;
    }

    public void setParent(self_t parent) {
        if (parent != null)
            checkNode(false, parent);
        this.parent = parent;
    }

    public List<self_t> getChildren() {
        return children;
    }

    public void setChildren(List<self_t> children) {
        if (children == null)
            throw new NullPointerException("children");
        this.children = children;
    }

    protected boolean isChildUnique() {
        return false;
    }

    public boolean addChild(self_t child) {
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

    public boolean removeChild(self_t child) {
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
     * @label.zh 根对象
     */
    @Derived
    public boolean isRoot() {
        return parent != null;
    }

    int indexOf(self_t child) {
        return children.indexOf(child);
    }

    /**
     * @label Index
     * @label.zh 序号
     */
    public int getIndex() {
        if (parent == null)
            return 0;
        self_t self = self();
        return parent.indexOf(self);
    }

    public int size() {
        return children.size();
    }

    /**
     * @label Depth
     * @label.zh 深度
     */
    @Derived
    public int getDepth() {
        int safeDepth = getSafeDepth();
        int depth = 0;
        self_t node = self();
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

    protected int getSafeDepth() {
        return 1000;
    }

    /**
     * @label First
     * @label.zh 首位
     */
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
    @Derived
    public boolean isLast() {
        if (parent == null)
            return true;
        else
            return getIndex() == parent.size() - 1;
    }

    /**
     * @label Chain
     * @label.zh 链
     */
    public List<self_t> getChain() {
        List<self_t> chain = new ArrayList<self_t>();
        self_t node = self();
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
    public String getGraphPrefix() {
        if (parent == null)
            return "";

        StringBuilder buf = new StringBuilder();

        if (!isLast())
            buf.append(" -| "); // _|-_
        else
            buf.append(" -` "); // _`-_

        self_t node = parent;
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
    protected void checkNode(boolean child, self_t node) {
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
        self_t node = self();
        for (int i = 0; i < order; i++) {

            self_t _node = node.getParent();
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

        for (self_t child : children)
            child.dump(out);
    }

    public void dump()
            throws IOException {
        dump(Stdio.cout);
    }

}
