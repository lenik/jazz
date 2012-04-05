package net.bodz.mda.xjdoc.rt;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TypeNameContext {

    static final Package javaLangPackage = Package.getPackage("java.lang");

    final Package contextPackage;
    final Map<String, Class<?>> importTypeMap = new HashMap<String, Class<?>>();

    public TypeNameContext(Class<?> contextType) {
        this(contextType.getPackage());
    }

    public TypeNameContext(Package contextPackage) {
        this.contextPackage = contextPackage;
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
        }

        String simpleName = type.getSimpleName();
        if (type.getPackage().equals(javaLangPackage))
            return simpleName;

        if (contextPackage != null && contextPackage.equals(type.getPackage()))
            try {
                // OPT: Cache. But JRE may have cached it already.
                Class.forName("java.lang." + simpleName);
            } catch (ClassNotFoundException e) {
                return simpleName;
            }

        Class<?> imported = importTypeMap.get(simpleName);
        if (imported == null) { // new class to import.
            importTypeMap.put(simpleName, type);
            return simpleName;
        }
        if (imported.equals(type)) // already imported
            return simpleName;
        else
            return type.getCanonicalName();
    }

    public String getMethodId(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        sb.append('(');
        int index = 0;
        for (Class<?> type : method.getParameterTypes()) {
            if (index++ != 0)
                sb.append(",");
            String typeName = importType(type);
            sb.append(typeName);
        }
        sb.append(')');
        return sb.toString();
    }

}
