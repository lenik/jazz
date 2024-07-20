package net.bodz.bas.t.tree;

import java.util.Arrays;
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

    node_t findPath(String path);

    node_t find(Iterable<String> path);

    default node_t find(String... path) {
        return find(Arrays.asList(path));
    }

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

    default void bottomUp(BiConsumer<List<String>, node_t> fn) {
        accept(new PathNodeVisitor<node_t>() {
            @Override
            public void bottomUp(List<String> path, node_t node) {
                fn.accept(path, node);
            }
        });
    }

}