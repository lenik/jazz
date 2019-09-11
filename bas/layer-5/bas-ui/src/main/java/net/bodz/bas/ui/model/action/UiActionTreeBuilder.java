package net.bodz.bas.ui.model.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class UiActionTreeBuilder {

    Map<Class<?>, UiActionNode> nodes;
    Set<UiActionNode> roots;

    public UiActionTreeBuilder() {
        nodes = new HashMap<Class<?>, UiActionNode>();
        roots = new TreeSet<UiActionNode>(UiActionNodeComparator.INSTANCE);
    }

    public Map<Class<?>, UiActionNode> getNodes() {
        return nodes;
    }

    public UiActionNode getNode(Class<?> locationClass) {
        return nodes.get(locationClass);
    }

    public Set<UiActionNode> getRoots() {
        return roots;
    }

    public Set<UiActionNode> buildTree(Object obj) {
        Class<?> objClass = obj == null ? null : obj.getClass();

        for (IAction action : ActionIndex.generalActions)
            addAction(action);

        if (objClass != null)
            for (List<IAction> list : ActionIndex.clsActions.join(objClass))
                for (IAction action : list)
                    addAction(action);

        for (IActionProvider provider : ActionProviderIndex.generalActionProviders)
            for (IAction action : provider.getActions(objClass))
                addAction(action);

        if (objClass != null)
            for (List<IActionProvider> list : ActionProviderIndex.clsActionProviders.join(objClass))
                for (IActionProvider provider : list)
                    for (IAction action : provider.getActions(objClass))
                        addAction(action);

        return getRoots();
    }

    void addAction(IAction action) {
        for (Class<?> locationClass : action.getLocations()) {
            UiActionNode parentNode = loadLocationNode(locationClass);
            new UiActionNode(parentNode, action);
        }
    }

    UiActionNode loadLocationNode(Class<?> c) {
        UiActionNode node = nodes.get(c);
        if (node == null) {
            UiLocationDecl decl = UiLocationIndex.get(c);
            if (decl == null)
                throw new IllegalArgumentException("UiLocationDecl isn't defined for " + c);

            UiLocationDecl parentDecl = decl.getParent();
            if (parentDecl != null) {
                UiActionNode parentNode = loadLocationNode(parentDecl.getClass());
                node = new UiActionNode(parentNode, decl);
            } else {
                node = new UiActionNode(decl);
                roots.add(node);
            }
            nodes.put(c, node);
        }
        return node;
    }

}
