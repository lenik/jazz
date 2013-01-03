package net.bodz.bas.c.loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.object.IdentityHashSet;

public class ClassLoaderAnalyzer {

    public static ClassLoaderNode[] mergeParents(ClassLoader... classLoaders) {
        ClassLoaderNode classLoaderNodes[] = new ClassLoaderNode[classLoaders.length];

        Map<ClassLoader, ClassLoaderNode> loaderNodeMap = new IdentityHashMap<>();

        for (int i = 0; i < classLoaders.length; i++) {
            ClassLoader loader = classLoaders[i];

            List<ClassLoader> vector = new ArrayList<>();
            while (loader != null) {
                vector.add(loader);
                loader = loader.getParent();
            }
            Collections.reverse(vector);

            ClassLoaderNode _lastNode = null;
            for (ClassLoader _loader : vector) {
                ClassLoaderNode _node = loaderNodeMap.get(_loader);
                if (_node == null) {
                    _node = new ClassLoaderNode(_lastNode, _loader);
                    loaderNodeMap.put(_loader, _node);
                }
                _lastNode = _node;
            }

            classLoaderNodes[i] = _lastNode;
        }
        return classLoaderNodes;
    }

    public static Set<ClassLoaderNode> getRoots(ClassLoaderNode... nodes) {
        Set<ClassLoaderNode> roots = new IdentityHashSet<>();
        for (ClassLoaderNode node : nodes) {
            ClassLoaderNode root = node.getRoot();
            roots.add(root);
        }
        return roots;
    }

}
