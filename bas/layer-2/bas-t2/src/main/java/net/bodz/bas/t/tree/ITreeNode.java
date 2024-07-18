package net.bodz.bas.t.tree;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public interface ITreeNode<node_t extends ITreeNode<node_t>> {

    boolean isMutable();

    /**
     * Get the parent node.
     *
     * @return <code>null</code> if this node doesn't have a parent.
     */
    node_t getParent();

    int size();

    boolean isEmpty();

    boolean contains(String childKey);

    boolean contains(ITreeNode<?> child);

    node_t getChild(String key);

    node_t getDescendant(String path);

    /**
     * Get or create the node by path.
     *
     * @param path
     *            Path of the node to be resolved. Can't be <code>null</code>.
     * @return The resolved node. If the <code>path</code> is empty, returns this node. All missing
     *         nodes along the path should be created implicitly. If it's failed to create any
     *         missing node, <code>null</code> is returned.
     *
     */
    node_t resolve(String path);

    /**
     * Get or create the node by path.
     *
     * @param path
     *            Path of the node to be resolved. Can't be <code>null</code>.
     * @return The resolved node. If the <code>path</code> is empty, returns this node. All missing
     *         nodes along the path should be created implicitly. If it's failed to create any
     *         missing node, <code>null</code> is returned.
     *
     */
    node_t resolve(String[] path);

    Set<String> childKeySet();

    Collection<? extends node_t> getChildren();

    Iterable<? extends node_t> descendants();

    String keyOf(ITreeNode<?> child);

    List<String> keysOf(ITreeNode<?> child);

    void accept(ITreeNodeVisitor<? super node_t> visitor);

    default void topDown(BiConsumer<List<String>, node_t> fn) {
        accept(new PathNodeVisitor<node_t>() {
            @Override
            public void topDown(List<String> path, node_t node) {
                fn.accept(path, node);
            }
        });
    }

}