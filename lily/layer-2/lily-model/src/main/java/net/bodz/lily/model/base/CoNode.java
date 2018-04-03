package net.bodz.lily.model.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;

public abstract class CoNode<self_t extends CoNode<self_t, Id>, Id>
        extends CoEntity<Id> {

    private static final long serialVersionUID = 1L;

    private int refCount;
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

    /**
     * Reference Count
     *
     * @label.zh 引用统计
     */
    @OfGroup(StdGroup.Statistics.class)
    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    @SuppressWarnings("unchecked")
    private self_t self() {
        return (self_t) this;
    }

    /**
     * @label Parent
     * @label.zh 父结点
     * @placeholder 选择一个父结点…
     */
    @OfGroup(StdGroup.Graph.class)
    public self_t getParent() {
        return parent;
    }

    public void setParent(self_t parent) {
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
    public List<self_t> getChildren() {
        return children;
    }

    public void setChildren(List<self_t> children) {
        if (children == null)
            throw new NullPointerException("children");
        this.children = children;
    }

    public boolean hasChild() {
        return children != null && !children.isEmpty();
    }

    /**
     * <p lang="zh">
     * 子结点是否要求唯一性。
     *
     * @label Child Unique
     * @label.zh 唯一性
     */
    @OfGroup(StdGroup.Graph.class)
    protected boolean isChildUnique() {
        return false;
    }

    public void detach() {
        if (parent == null)
            return;

        @SuppressWarnings("unchecked")
        self_t self = (self_t) this;
        parent.removeChild(self);
        parent = null;
    }

    public boolean attach(self_t parent) {
        detach();
        this.parent = parent;

        @SuppressWarnings("unchecked")
        self_t self = (self_t) this;
        return parent.addChild(self);
    }

    boolean addChild(self_t child) {
        if (child == null)
            throw new NullPointerException("child");

        checkNode(true, child);

        if (isChildUnique())
            if (children.contains(child))
                return false;

        children.add(child);
        return true;
    }

    boolean removeChild(self_t child) {
        return children.remove(child);
    }

    /**
     * <p lang="zh">
     * 指出是否为最顶层的结点。
     *
     * @label Root
     * @label.zh 根结点
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public boolean isRoot() {
        return parent != null;
    }

    int indexOf(self_t child) {
        return children.indexOf(child);
    }

    /**
     * <p lang="zh">
     * 结点的序号。
     *
     * @label Index
     * @label.zh 序号
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
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
     * <p lang="zh">
     * 结点在树中的深度或层次。
     *
     * @label Depth
     * @label.zh 深度
     */
    @OfGroup(StdGroup.Graph.class)
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

    /**
     * <p lang="zh">
     * 子结点的最大（安全）深度。
     *
     * @label Safe Depth
     * @label.zh 安全深度
     */
    @OfGroup(StdGroup.Graph.class)
    @DetailLevel(DetailLevel.HIDDEN)
    protected int getSafeDepth() {
        return 1000;
    }

    /**
     * <p lang="zh">
     * 指出结点在并排结点中是否为首位。
     *
     * @label First
     * @label.zh 首位
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public boolean isFirst() {
        if (parent == null)
            return true;
        else
            return parent.isFirst(this);
    }

    public boolean isFirst(Object child) {
        if (children == null || children.isEmpty())
            return false;
        return children.get(0) == child;
    }

    /**
     * <p lang="zh">
     * 指出结点在并排结点中是否为末位。
     *
     * @label Last
     * @label.zh 末位
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public boolean isLast() {
        if (parent == null)
            return true;
        return parent.isLast(this);
    }

    public boolean isLast(Object child) {
        if (children == null || children.isEmpty())
            return false;
        return children.get(children.size() - 1) == child;
    }

    /**
     * <p lang="zh">
     * 结点所在完整路径。
     *
     * @label Node Path
     * @label.zh 结点路径
     */
    @OfGroup(StdGroup.Graph.class)
    public String getNodePath() {
        StringBuilder sb = new StringBuilder();
        int d = 0;
        for (self_t node : toList()) {
            if (d++ != 0)
                sb.append("/");
            sb.append(node.getNodeLabel());
        }
        return sb.toString();
    }

    public CoNodeChain<self_t> toList() {
        CoNodeChain<self_t> chain = new CoNodeChain<self_t>();
        self_t node = self();
        while (node != null) {
            chain.add(node);
            node = node.getParent();
        }
        Collections.reverse(chain);
        return chain;
    }

    /**
     * <p lang="zh">
     * 结点在生成树图案中的字符图案前缀。
     *
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
     * <p lang="zh">
     * 指出的简短的标签名称。
     *
     * @label Node Label
     * @label.zh 结点标签
     */
    @OfGroup(StdGroup.Graph.class)
    @Derived
    public String getNodeLabel() {
        StringBuilder sb = new StringBuilder();
        String codeName = getCodeName();
        if (codeName != null) {
            sb.append(codeName);
            sb.append(":");
        }

        String label = getLabel();
        if (label != null) {
            sb.append('"');
            sb.append(label);
            sb.append('"');
        }

        if (sb.length() == 0)
            sb.append("(n/a)");
        return sb.toString();
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
        if (child) {
            Class<?> parentType = getClass();
            if (!parentType.isInstance(node))
                throw new IllegalArgumentException("Inconsistent node type: parent=" + parentType + ", child="
                        + node.getClass());
        } else {
            Class<?> parentType = node.getClass();
            if (!parentType.isInstance(this))
                throw new IllegalArgumentException("Inconsistent node type: parent=" + parentType + ", child="
                        + getClass());
        }
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

    public Set<self_t> closure() {
        final Set<self_t> closure = new LinkedHashSet<>();
        accept(new ICoNodeVisitor<self_t>() {

            @Override
            public boolean begin(self_t node) {
                closure.add(node);
                return true;
            }

            @Override
            public void end(self_t node) {
            }

        });
        return closure;
    }

    public void accept(ICoNodeVisitor<? super self_t> visitor) {
        @SuppressWarnings("unchecked")
        self_t self = (self_t) this;
        boolean included = visitor.begin(self);

        if (included) {
            for (self_t child : getChildren())
                child.accept(visitor);

            visitor.end(self);
        }
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
