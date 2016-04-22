package net.bodz.bas.ui.model.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class UiLocationTreeBuilder {

    Map<Class<?>, UiLocationNode> nodes;
    Set<UiLocationNode> roots;

    public UiLocationTreeBuilder() {
        nodes = new HashMap<>();
        roots = new TreeSet<>(UiLocationNodeComparator.INSTANCE);
    }

    public Map<Class<?>, UiLocationNode> getNodes() {
        return nodes;
    }

    public UiLocationNode getNode(Class<?> locationClass) {
        return nodes.get(locationClass);
    }

    public Set<UiLocationNode> getRoots() {
        return roots;
    }

    public void buildTree(Object obj) {
        Class<?> objClass = obj.getClass();

        for (IAction action : ActionIndex.generalActions)
            addAction(action);

        for (List<IAction> list : ActionIndex.clsActions.join(objClass))
            for (IAction action : list)
                addAction(action);

        for (IActionProvider provider : ActionProviderIndex.generalActionProviders)
            for (IAction action : provider.getActions(objClass))
                addAction(action);

        for (List<IActionProvider> list : ActionProviderIndex.clsActionProviders.join(objClass))
            for (IActionProvider provider : list)
                for (IAction action : provider.getActions(objClass))
                    addAction(action);
    }

    void addAction(IAction action) {
        for (Class<?> locationClass : action.getLocations()) {
            UiLocationNode node = loadNode(locationClass);
            node.addAction(action);
        }
    }

    UiLocationNode loadNode(Class<?> c) {
        UiLocationNode node = nodes.get(c);
        if (node == null) {
            UiLocationDecl decl = UiLocationIndex.get(c);

            UiLocationDecl parentDecl = decl.getParent();
            if (parentDecl != null) {
                UiLocationNode parentNode = loadNode(parentDecl.getClass());
                node = new UiLocationNode(decl, parentNode);
            } else {
                node = new UiLocationNode(decl);
                roots.add(node);
            }
            nodes.put(c, node);
        }
        return node;
    }

}
