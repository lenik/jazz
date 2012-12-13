package net.bodz.mda.xjdoc.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.rtx.INegotiation;

import com.thoughtworks.qdox.model.Type;

public class ImportMap {

    final String localPackageName;
    ImportMap parent;

    /** simple-name --> full-qualified name */
    final Map<String, String> map = new HashMap<String, String>();

    public ImportMap(Class<?> contextType) {
        this(null, contextType.getPackage().getName());
    }

    public ImportMap(String localPackageName) {
        this(null, localPackageName);
    }

    public ImportMap(ImportMap parent, String localPackageName) {
        this.parent = parent;
        this.localPackageName = localPackageName;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public String add(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        if (type.isPrimitive())
            return type.getName();
        if (type.isArray()) {
            Class<?> componentType = type.getComponentType();
            String component = add(componentType);
            return component + "[]";
        } else {
            return add(type.getCanonicalName());
        }
    }

    public String add(Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(add(type.getFullyQualifiedName()));
        int dims = type.getDimensions();
        for (int i = 0; i < dims; i++)
            sb.append("[]");
        return sb.toString();
    }

    /**
     * Add type to imported-list, and returns the simple name (type alias).
     */
    public String add(String fqcn) {
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

        String importedFqcn = map.get(simpleName);
        if (importedFqcn == null) { // new class to import.
            map.put(simpleName, fqcn);
            return simpleName;
        }

        if (importedFqcn.equals(fqcn)) // already imported
            return simpleName;
        else
            return fqcn;
    }

    public String normalize(String name) {
        if (name == null)
            throw new NullPointerException("name");

        String fqcn = map.get(name);
        if (fqcn != null)
            return fqcn;

        // String localFqcn = localPackageName + "." + name;

        if (parent != null) {
            fqcn = parent.normalize(name);
            if (!fqcn.equals(name)) {
                // copy-on-expand for shared imports.
                add(fqcn);
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

    public static ImportMap getInstance(INegotiation negotiation) {
        ImportMap importMap = negotiation.get(ImportMap.class);
        if (importMap == null)
            throw new NullPointerException("ImportMap is not provided in the negotiation.");
        return importMap;
    }

}
