package net.bodz.mda.xjdoc.util;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.qdox.model.Type;

public class TypeNameContext {

    final String contextPackageName;

    /** simple-name --> full-qualified name */
    final Map<String, String> importTypeMap = new HashMap<String, String>();

    public TypeNameContext(Class<?> contextType) {
        this(contextType.getPackage().getName());
    }

    public TypeNameContext(String contextPackageName) {
        this.contextPackageName = contextPackageName;
    }

    /**
     * Add type to imported-list, and returns the simple name (type alias).
     */
    public String importType(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        if (type.isPrimitive())
            return type.getName();
        if (type.isArray()) {
            Class<?> componentType = type.getComponentType();
            String component = importType(componentType);
            return component + "[]";
        } else {
            return importType(type.getCanonicalName());
        }
    }

    public String importType(Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(importType(type.getFullyQualifiedName()));
        int dims = type.getDimensions();
        for (int i = 0; i < dims; i++)
            sb.append("[]");
        return sb.toString();
    }

    public String importType(String fqcn) {
        String simpleName;
        String packageName;
        int lastDot = fqcn.lastIndexOf('.');
        if (lastDot == -1) {
            simpleName = fqcn;
            packageName = "";
        } else {
            simpleName = fqcn.substring(lastDot + 1);
            packageName = fqcn.substring(0, lastDot);
        }

        if ("java.lang".equals(packageName))
            return simpleName;

        // With-in the same package? Never import friend class.
        if (contextPackageName != null && contextPackageName.equals(packageName))
            try {
                // OPT: Cache. But JRE may have cached it already.
                Class.forName("java.lang." + simpleName);
                return fqcn;
            } catch (ClassNotFoundException e) {
                return simpleName;
            }

        String importedFqcn = importTypeMap.get(simpleName);
        if (importedFqcn == null) { // new class to import.
            importTypeMap.put(simpleName, fqcn);
            return simpleName;
        }

        if (importedFqcn.equals(fqcn)) // already imported
            return simpleName;
        else
            return fqcn;
    }

}
