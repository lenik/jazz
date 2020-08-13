package net.bodz.bas.site.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PathMapNode<T>
// implements IJsonSerializable
{

    PathMap<T> pathMap;
    Map<String, PathMapNode<T>> children;
    Map<String, T> attributes;

    public PathMapNode(PathMap<T> pathMap) {
        this.pathMap = pathMap;
        this.children = pathMap.newMap();
        this.attributes = pathMap.newMap();
    }

    public PathMapNode<T> getChild(String key) {
        return children.get(key);
    }

    public synchronized PathMapNode<T> getOrCreateChild(String key) {
        PathMapNode<T> node = children.get(key);
        if (node == null) {
            node = new PathMapNode<>(pathMap);
            children.put(key, node);
        }
        return node;
    }

    public <X extends Exception> void accept(IVisitor<T, X> visitor)
            throws X {
        List<Collection<String>> keySets = new ArrayList<>();
        keySets.add(children.keySet());
        keySets.add(attributes.keySet());
        visitor.combineKeys(keySets);

        for (Collection<String> keySet : keySets)
            for (String key : keySet) {
                PathMapNode<T> child = children.get(key);
                if (child != null) {
                    visitor.visitNode(key, child);
                    continue;
                }
                T value = attributes.get(key);
                visitor.visitAttribute(key, value);
            }
    }

    public static interface IVisitor<T, X extends Exception> {

        void combineKeys(List<Collection<String>> keySets);

        void visitNode(String key, PathMapNode<T> child)
                throws X;

        void visitAttribute(String key, T value)
                throws X;

    }

}
