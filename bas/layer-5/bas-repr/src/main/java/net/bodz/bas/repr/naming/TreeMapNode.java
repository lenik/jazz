package net.bodz.bas.repr.naming;

import java.io.Serializable;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapNode<T>
        extends NamedNode
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<String, T> map = new TreeMap<String, T>();
    private final Map<T, String> reverseMap = new IdentityHashMap<T, String>();

    public TreeMapNode(Class<?> baseType) {
        super(baseType, null);
    }

    public TreeMapNode(String name, Class<?> baseType) {
        super(name, baseType, null);
    }

    public TreeMapNode(INamedNode parent) {
        super(null, parent);
    }

    public TreeMapNode(String name, INamedNode parent) {
        super(name, null, parent);
    }

    public TreeMapNode(Class<?> baseType, INamedNode parent) {
        super(baseType, parent);
    }

    public TreeMapNode(String name, Class<?> baseType, INamedNode parent) {
        super(name, baseType, parent);
    }

    @Override
    public boolean containsChild(Object obj) {
        return reverseMap.containsKey(obj);
    }

    @Override
    public Object getChild(String locationToken) {
        return map.get(locationToken);
    }

    @Override
    public String findChild(Object obj) {
        return reverseMap.get(obj);
    }

    public void addChild(String locationToken, T obj) {
        map.put(locationToken, obj);
        reverseMap.put(obj, locationToken);
    }

    public void removeChild(Object obj) {
        String locationToken = reverseMap.remove(obj);
        map.remove(locationToken);
    }

    @Override
    public Collection<String> getChildrenNames() {
        return map.keySet();
    }

    @Override
    public Collection<?> getChildren() {
        return map.values();
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        buf.append("node ");
        buf.append(name);
        buf.append(" {");
        for (Map.Entry<String, T> entry : map.entrySet()) {
            String locationToken = entry.getKey();
            T obj = entry.getValue();

            buf.append(locationToken);
            buf.append(" => ");
            buf.append(String.valueOf(obj));
            buf.append('\n');
        }
        buf.append("}");
        return buf.toString();
    }

}
