package net.bodz.bas.db.sql;

import net.bodz.bas.t.map.SetMap;

public class DataTypes {

    final SetMap<Class<?>, DataType> classMap = new SetMap<>();
    final SetMap<Integer, DataType> sqlTypeMap = new SetMap<>();
    final SetMap<String, DataType> sqlTypeNameMap = new SetMap<>();

    public DataType getDefault(Class<?> javaClass) {
        return classMap.getFirstOfSet(javaClass);
    }

    public DataType getDefault(int sqlType) {
        return sqlTypeMap.getFirstOfSet(sqlType);
    }

    public DataType getDefault(String sqlTypeName) {
        return sqlTypeNameMap.getFirstOfSet(sqlTypeName);
    }

    public void add(DataType... dataTypes) {
        for (DataType dataType : dataTypes)
            add(dataType);
    }

    public void add(DataType type) {
        classMap.addToSet(type.getJavaClass(), type);
        sqlTypeMap.addToSet(type.getSqlType(), type);
        sqlTypeNameMap.addToSet(type.getSqlTypeName(), type);
    }

    public void remove(DataType type) {
        classMap.removeFromSet(type.getJavaClass(), type);
        sqlTypeMap.removeFromSet(type.getSqlType(), type);
        sqlTypeNameMap.removeFromSet(type.getSqlTypeName(), type);
    }

    public void replace(DataType oldType, DataType newType) {
        remove(oldType);
        add(newType);
    }

}
