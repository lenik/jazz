package net.bodz.lily.entity.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.t.catalog.ITable;
import net.bodz.bas.t.catalog.ITableMetadata;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.tuple.QualifiedName;

public class EntityTypes {

    static final Map<Class<?>, IEntityTypeInfo> cache = new HashMap<>();

    public static synchronized IEntityTypeInfo getTypeInfo(Class<?> entityClass) {
        IEntityTypeInfo typeInfo = cache.get(entityClass);
        if (typeInfo == null)
            synchronized (cache) {
                typeInfo = cache.get(entityClass);
                if (typeInfo == null) {
                    typeInfo = new DefaultEntityTypeInfo(entityClass);
                    cache.put(entityClass, typeInfo);
                }
            }
        return typeInfo;
    }

    static final Map<String, Class<?>> mapperClasses = new HashMap<>();
    static final Map<String, Class<?>> entityClassNames = new HashMap<>();
    static final ListMap<String, Class<?>> entityClassSimpleNames = new ListMap<>();

    static {
        for (Class<? extends IMapper> mapperClass : IndexedTypes.list(IMapper.class, true)) {
            mapperClasses.put(mapperClass.getName(), mapperClass);

            String packageName = mapperClass.getPackageName();
            if (!packageName.endsWith(".dao"))
                continue;
            String simpleName = mapperClass.getSimpleName();
            if (!simpleName.endsWith("Mapper"))
                continue;
            packageName = packageName.substring(0, packageName.length() - 4);
            simpleName = simpleName.substring(0, simpleName.length() - 6);

            String qName = packageName + "." + simpleName;
            Class<?> entityClass;
            try {
                entityClass = Class.forName(qName);
            } catch (ClassNotFoundException e) {
                continue;
            }

            entityClassNames.put(qName, entityClass);
            entityClassSimpleNames.addToList(simpleName, entityClass);
        }
    }

    public static Class<?> getMapperClass(String mapperClassName) {
        return mapperClasses.get(mapperClassName);
    }

    public static Class<?> getMapperClassFor(String entityClassName) {
        return getMapperClassFor(entityClassName, false);
    }

    public static Class<?> getMapperClassFor(String entityClassName, boolean simpleName) {
        IEntityTypeInfo typeInfo = getTypeInfo(entityClassName, simpleName);
        return typeInfo == null ? null : typeInfo.getMapperClass();
    }

    public static IEntityTypeInfo getTypeInfo(String entityClassName) {
        return getTypeInfo(entityClassName, false);
    }

    public static IEntityTypeInfo getTypeInfo(String entityClassName, boolean simpleName) {
        Class<?> entityClass;
        if (simpleName) {
            List<Class<?>> classes = entityClassSimpleNames.get(entityClassName);
            if (classes == null || classes.isEmpty())
                return null;
            if (classes.size() != 1)
                throw new IllegalStateException("name is ambiguous: possible values are  " + classes);
            entityClass = classes.getFirst();
        } else
            entityClass = entityClassNames.get(entityClassName);

        if (entityClass == null)
            return null;

        IEntityTypeInfo typeInfo = getTypeInfo(entityClass);
        return typeInfo;
    }

    public static final Function<String, IEntityTypeInfo> qTypeMap = EntityTypes::getTypeInfo;
    public static final Function<String, IEntityTypeInfo> simpleTypeMap = name -> getTypeInfo(name, true);

    public static final Function<ITable, String> qTypeNaming = table -> {
        ITableMetadata metadata = table.getMetadata();
        if (metadata != null)
            return metadata.getJavaTypeName();
        return null;
    };

    public static final Function<ITable, String> simpleTypeNaming = table -> {
        ITableMetadata metadata = table.getMetadata();
        if (metadata != null) {
            QualifiedName javaType = metadata.getJavaType();
            if (javaType != null)
                return javaType.name;
        }
        return null;
    };


}
