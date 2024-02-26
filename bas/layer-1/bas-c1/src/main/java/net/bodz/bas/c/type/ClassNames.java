package net.bodz.bas.c.type;

public class ClassNames {

    public static String encodeByteCode(String className) {
        switch (className) {
        case "byte":
            return "B";
        case "short":
            return "S";
        case "int":
            return "I";
        case "long":
            return "J";
        case "float":
            return "F";
        case "double":
            return "D";
        case "boolean":
            return "Z";
        case "char":
            return "C";
        default:
            return "L" + className + ";";
        }
    }

    public static String normalize(String canonicalName) {
        int dim = 0;
        while (canonicalName.endsWith("[]")) {
            canonicalName = canonicalName.substring(0, canonicalName.length() - 2);
            dim++;
        }
        if (dim > 0) {
            StringBuilder sb = new StringBuilder(canonicalName.length() + 2 + dim);
            for (int i = 0; i < dim; i++)
                sb.append('[');
            sb.append(encodeByteCode(canonicalName));
            return sb.toString();
        } else {
            return canonicalName;
        }
    }

    /**
     * @name Can be primitive type name, canonical class name or normalized class name.
     */
    public static Class<?> resolve(String name)
            throws ClassNotFoundException {
        switch (name) {
        case "byte":
            return byte.class;
        case "short":
            return short.class;
        case "int":
            return int.class;
        case "long":
            return long.class;
        case "float":
            return float.class;
        case "double":
            return double.class;
        case "boolean":
            return boolean.class;
        case "char":
            return char.class;
        case "void":
            return void.class;
        default:
            name = normalize(name);
            return Class.forName(name);
        }
    }

}
