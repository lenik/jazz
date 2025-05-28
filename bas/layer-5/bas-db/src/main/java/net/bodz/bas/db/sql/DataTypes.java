package net.bodz.bas.db.sql;

import net.bodz.bas.t.map.SetMap;

public class DataTypes {

    final SetMap<Class<?>, DataType> classMap = new SetMap<>();
    final SetMap<Class<?>, DataType> altClassMap = new SetMap<>();
    final SetMap<Integer, DataType> sqlTypeMap = new SetMap<>();
    final SetMap<String, DataType> sqlTypeNameMap = new SetMap<>();

    public DataType getDefault(Class<?> javaClass) {
        DataType type = classMap.getFirstOfSet(javaClass);
        if (type != null)
            return type;
        type = altClassMap.getFirstOfSet(javaClass);
        return type;
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
        for (Class<?> altClass : type.altJavaClasses)
            altClassMap.addToSet(altClass, type);

        sqlTypeMap.addToSet(type.getSqlType(), type);
        sqlTypeNameMap.addToSet(type.getSqlTypeName(), type);
    }

    public void remove(DataType type) {
        classMap.removeFromSet(type.getJavaClass(), type);
        for (Class<?> altClass : type.altJavaClasses)
            altClassMap.removeFromSet(altClass, type);

        sqlTypeMap.removeFromSet(type.getSqlType(), type);
        sqlTypeNameMap.removeFromSet(type.getSqlTypeName(), type);
    }

    public void replace(DataType oldType, DataType newType) {
        remove(oldType);
        add(newType);
    }

}
