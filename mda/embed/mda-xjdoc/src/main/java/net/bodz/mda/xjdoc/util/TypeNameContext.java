package net.bodz.mda.xjdoc.util;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.qdox.model.Type;

public class TypeNameContext {

    final String localPackageName;
    TypeNameContext parent;

    /** simple-name --> full-qualified name */
    final Map<String, String> typeMap = new HashMap<String, String>();

    public TypeNameContext(Class<?> contextType) {
        this(null, contextType.getPackage().getName());
    }

    public TypeNameContext(String localPackageName) {
        this(null, localPackageName);
    }

    public TypeNameContext(TypeNameContext parent, String localPackageName) {
        this.parent = parent;
        this.localPackageName = localPackageName;
    }

    public Map<String, String> getImportMap() {
        return typeMap;
    }

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
            return importTypeName(type.getCanonicalName());
        }
    }

    public String importType(Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(importTypeName(type.getFullyQualifiedName()));
        int dims = type.getDimensions();
        for (int i = 0; i < dims; i++)
            sb.append("[]");
        return sb.toString();
    }

    /**
     * Add type to imported-list, and returns the simple name (type alias).
     */
    public String importTypeName(String fqcn) {
        int lastDot = fqcn.lastIndexOf('.');
        if (lastDot == -1)
            return fqcn;

        String simpleName = fqcn.substring(lastDot + 1);
        String packageName = fqcn.substring(0, lastDot);

        if ("java.lang".equals(packageName))
            return simpleName;

        // With-in the same package? Never import friend class.
        if (localPackageName != null && localPackageName.equals(packageName))
            try {
                // OPT: Cache. But JRE may have cached it already.
                Class.forName("java.lang." + simpleName);
                return fqcn;
            } catch (ClassNotFoundException e) {
                return simpleName;
            }

        String importedFqcn = typeMap.get(simpleName);
        if (importedFqcn == null) { // new class to import.
            typeMap.put(simpleName, fqcn);
            return simpleName;
        }

        if (importedFqcn.equals(fqcn)) // already imported
            return simpleName;
        else
            return fqcn;
    }

    public String expand(String name) {
        if (name == null)
            throw new NullPointerException("name");

        String fqcn = typeMap.get(name);
        if (fqcn != null)
            return fqcn;

        // String localFqcn = localPackageName + "." + name;

        if (parent != null) {
            fqcn = parent.expand(name);
            if (!fqcn.equals(name)) {
                // copy-on-expand for shared imports.
                importTypeName(fqcn);
                return fqcn;
            }
        }

        fqcn = expandJavaLang(name);
        return fqcn;
    }

    static String expandJavaLang(String name) {
        String fqcn = "java.lang." + name;
        try {
            Class.forName(fqcn);
            return fqcn;
        } catch (ClassNotFoundException e) {
            return name;
        }
    }

// public static TypeNameContext negotiate(INegotiation negotiation)
// throws NegotiationException {
// for (NegotiationParameter np : negotiation)
// if (np.accept(TypeNameContext.class, true))
// return (TypeNameContext) np.getValue();
// return null;
// }

}
