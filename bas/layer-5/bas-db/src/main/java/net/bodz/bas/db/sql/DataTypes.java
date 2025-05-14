package net.bodz.bas.db.sql;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;

public class DataTypes {

    final Map<Class<?>, DataType> classDefaults = new HashMap<>();
    final Map<Integer, DataType> sqlTypeDefaults = new HashMap<>();
    final Map<String, DataType> sqlTypeNameDefaults = new HashMap<>();

    public DataType getDefault(Class<?> javaClass) {
        return classDefaults.get(javaClass);
    }

    public DataType getDefault(int sqlType) {
        return sqlTypeDefaults.get(sqlType);
    }

    public DataType getDefault(String sqlTypeName) {
        return sqlTypeNameDefaults.get(sqlTypeName);
    }

    public void setDefault(Class<?> javaClass, @NotNull DataType dataType) {
        classDefaults.put(javaClass, dataType);
    }

    public void setDefault(int sqlType, @NotNull DataType dataType) {
        sqlTypeDefaults.put(sqlType, dataType);
    }

    public void setDefault(String sqlTypeName, @NotNull DataType dataType) {
        sqlTypeNameDefaults.put(sqlTypeName, dataType);
    }

    public void add(DataType dataType) {
        putIfAbsentOrError(classDefaults, //
                dataType.getJavaClass(), dataType);
    }

    public void addNameDefault(DataType dataType) {
        add(dataType);
        putIfAbsentOrError(sqlTypeNameDefaults, //
                dataType.getSqlTypeName(), dataType);
    }

    public void addDefault(DataType dataType) {
        addNameDefault(dataType);
        putIfAbsentOrError(sqlTypeDefaults, //
                dataType.getSqlType(), dataType);
    }

    static <K, V> void putIfAbsentOrError(@NotNull Map<K, V> map, K key, V value) {
        if (map.containsKey(key)) {
            V oldValue = map.get(key);
            throw new IllegalStateException("Key %s is already existed, the value was: " + oldValue);
        }
        map.put(key, value);
    }

}
