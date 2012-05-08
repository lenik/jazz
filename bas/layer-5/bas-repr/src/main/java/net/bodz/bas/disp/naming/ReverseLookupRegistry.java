package net.bodz.bas.disp.naming;

import java.util.Map.Entry;

import net.bodz.bas.c.type.TypePrMap;
import net.bodz.bas.err.IllegalUsageException;

public class ReverseLookupRegistry {

    private TypePrMap<NamedNodeSet> typemap;

    public ReverseLookupRegistry() {
        typemap = new TypePrMap<NamedNodeSet>();
    }

    public synchronized <T> void register(INamedNode node) {
        Class<?> baseType = node.getChildType();
        if (baseType == null)
            throw new NullPointerException("null baseType in node of " + node.getClass());

        NamedNodeSet nodesForSameType = (NamedNodeSet) typemap.get(baseType);

        if (nodesForSameType == null) {
            nodesForSameType = new NamedNodeSet();
            typemap.put(baseType, nodesForSameType);
        }
        nodesForSameType.add(node);
    }

    public synchronized void unregister(INamedNode locator) {
        Class<?> baseType = locator.getChildType();

        NamedNodeSet nodesForSameType = typemap.get(baseType);

        if (nodesForSameType != null) {
            nodesForSameType.remove(locator);
            if (nodesForSameType.isEmpty())
                typemap.remove(baseType);
        }
    }

    /**
     * Get (any) object locator for the corresponding object type.
     * 
     * @return The locator with highest priority for a specific object type is returned. Return
     *         <code>null</code> if none available.
     */
    public <T> INamedNode whoseClass(Class<?> objectType) {
        if (objectType == null)
            throw new NullPointerException("objectType");

        Entry<Class<?>, NamedNodeSet> floorEntry = typemap.floorEntry(objectType);

        while (floorEntry != null) {
            Class<?> baseType = floorEntry.getKey();
            NamedNodeSet nodesForSameType = (NamedNodeSet) floorEntry.getValue();

            if (nodesForSameType != null && !nodesForSameType.isEmpty())
                return nodesForSameType.iterator().next();

            floorEntry = typemap.lowerEntry(baseType);
            // Assured by Preorder.
            // assert floorEntry.getKey().isAssignableFrom(objectType);
        }
        return null;
    }

    public <T> INamedNode whoseObject(T object) {
        if (object == null)
            throw new NullPointerException("object");

        // Local tighten.
        if (object instanceof INamedNode) {
            INamedNode locatorObj = (INamedNode) object;
            INamedNode parent = locatorObj.getParent();
            if (parent != null)
                return parent;
        }

        Class<?> objectType = object.getClass();

        Entry<Class<?>, NamedNodeSet> floorEntry = typemap.floorEntry(objectType);

        while (floorEntry != null) {
            Class<?> baseType = floorEntry.getKey();
            NamedNodeSet nodeSet = (NamedNodeSet) floorEntry.getValue();

            if (nodeSet != null) {
                for (INamedNode _node : nodeSet) {
                    String location = _node.findChild(object);
                    if (location != null)
                        return _node;
                }
            }

            floorEntry = typemap.lowerEntry(baseType);
            // Assured by Preorder.
            // assert floorEntry.getKey().isAssignableFrom(objectType);
        }
        return null;
    }

    public LookupChain lookup(Object obj, LookupChain inner) {
        // Should be the node who did the dispatching.
        INamedNode node = whoseObject(obj);
        if (node == null)
            if (inner == null)
                return null;
            else
                return new LookupChain(null, obj, inner);

        String location = node.findChild(obj);
        if (location == null)
            throw new IllegalUsageException("Location isn't set for " + obj + " in " + node);

        LookupChain head = new LookupChain(location, obj, inner);

        return lookup(node, head);
    }

    /**
     * Get the leaf token of a path refers to this object.
     * 
     * @return <code>null</code> if obj isn't registered with this registry.
     */
    public String getLocation(Object obj) {
        LookupChain chain = lookup(obj, null);
        if (chain == null)
            return null;
        else
            return chain.join();
    }

    private static ReverseLookupRegistry instance = new ReverseLookupRegistry();

    public static ReverseLookupRegistry getInstance() {
        return instance;
    }

}