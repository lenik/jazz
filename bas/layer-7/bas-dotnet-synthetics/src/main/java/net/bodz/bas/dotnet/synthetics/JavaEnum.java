package net.bodz.bas.dotnet.synthetics;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.collection.util.ClassLocal;

public abstract class JavaEnum
        implements Comparable<JavaEnum> {

    private final String name;
    private final int ordinal;

    public JavaEnum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        register(this);
    }

    // List<JavaEnum> or JavaEnum[]
    private static ClassLocal<Map<String, JavaEnum>> clNameMap;
    private static ClassLocal<Object> clValues;

    protected void register(JavaEnum constDecl) {
        Class<?> type = getClass();
        Map<String, JavaEnum> nameMap = clNameMap.get(type);
        if (nameMap == null) {
            nameMap = new HashMap<String, JavaEnum>();
            clNameMap.put(type, nameMap);
        } else {
            assert !nameMap.containsKey(constDecl.name) : "name exists";
            clValues.remove(type);
        }
        nameMap.put(constDecl.name, constDecl);
    }

    @Override
    public int compareTo(JavaEnum o) {
        if (ordinal == o.ordinal)
            return -1;
        return ordinal < o.ordinal ? -1 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
        // if (obj == null)
        // return false;
        // // the concrete enum class must be final.
        // if (this.getClass() != obj.getClass())
        // return false;
        // JavaEnum e = (JavaEnum) obj;
        // return ordinal == e.ordinal;
    }

    public static <T extends JavaEnum> T valueOf(Class<T> type, String name) {
        Map<String, JavaEnum> nameMap = clNameMap.get(type);
        if (nameMap != null) {
            T value = (T) nameMap.get(name);
            if (value != null)
                return value;
            // try {
            // int ordinal = Integer.parseInt(s);
            // for (JavaEnum constDecl : nameMap.values())
            // if (constDecl.ordinal == ordinal)
            // return (T) constDecl;
            // } catch (NumberFormatException e) {
            // }
        }
        // throw exception?
        throw new NoSuchConstException(type + "." + name);
    }

    protected static <T extends JavaEnum> T[] _values(Class<T> type) {
        T[] values = (T[]) clValues.get(type);

        if (values == null) {
            Map<String, JavaEnum> nameMap = clNameMap.get(type);

            T[] empty = (T[]) Array.newInstance(type, 0);
            if (nameMap == null)
                values = empty;
            else
                values = nameMap.values().toArray(empty);

            clValues.put(type, values);
        }
        return values;
    }

    // protected static void valueOf(String name) {
    // return valueOf(ENUM.class, name);
    // }

    // public static ENUM[] values() {
    // return _values(ENUM.class);
    // }

}
