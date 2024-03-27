package net.bodz.lily.entity.type;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.bean.BeanTypeProvider;
import net.bodz.lily.concrete.StructRowCriteriaBuilder;
import net.bodz.lily.criteria.ICriteriaBuilder;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.entity.PrimaryKeyColumns;
import net.bodz.lily.entity.PrimaryKeyProperties;
import net.bodz.lily.entity.StrVar;

public class DefaultEntityTypeInfo
        implements
            IEntityTypeInfo {

    final Class<?> entityClass;
    final IType potatoType;

    final Class<?> idClass;
    final Class<?> mapperClass;
    final Class<?> criteriaBuilderClass;

    final String[] primaryKeyColumns;
    final String[] primaryKeyPropertyNames;
    final Map<String, IProperty> primaryKeyProperties = new LinkedHashMap<>();

//    ColumnProperty[] idProperties;

    public DefaultEntityTypeInfo(Class<?> entityClass) {
        this.entityClass = entityClass;
        potatoType = BeanTypeProvider.getInstance().loadType(entityClass);

        idClass = IdFn._getIdType(entityClass);
        mapperClass = IMapper.fn.getMapperClass(entityClass);
        criteriaBuilderClass = StructRowCriteriaBuilder.findCriteriaBuilderClass(entityClass);

        PrimaryKeyColumns aColumns = entityClass.getAnnotation(PrimaryKeyColumns.class);
        if (aColumns != null)
            primaryKeyColumns = aColumns.value();
        else
            primaryKeyColumns = new String[0];

        PrimaryKeyProperties aProperties = entityClass.getAnnotation(PrimaryKeyProperties.class);
        if (aProperties != null) {
            primaryKeyPropertyNames = aProperties.value();
            for (String name : primaryKeyPropertyNames) {
                IProperty property = potatoType.getProperty(name);
                if (property == null)
                    throw new IllegalUsageException("invalid property name: " + name);
                primaryKeyProperties.put(name, property);
            }
        } else {
            primaryKeyPropertyNames = new String[0];
        }

//        if (idClass != null && IIdentity.class.isAssignableFrom(idClass)) {
//            BeanInfo beanInfo;
//            try {
//                beanInfo = Introspector.getBeanInfo(idClass);
//            } catch (IntrospectionException e) {
//                throw new RuntimeException(e.getMessage(), e);
//            }
//            List<ColumnProperty> columns = new ArrayList<>();
//            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
//                ColumnProperty property = new ColumnProperty(pd);
//                if (property.getColumn() != null)
//                    columns.add(property);
//            }
//            int n = columns.size();
//            idProperties = new ColumnProperty[n];
//            for (int i = 0; i < n; i++)
//                idProperties[i] = columns.get(i);
//
//            Comparator<ColumnProperty> order;
//            order = new SourceDeclaredPropertyOrder(idClass);
//            order = ColumnPropertyOrder.INSTANCE;
//            Arrays.sort(idProperties, order);
//        } // idClass != null
    }

    @Override
    public Class<?> getEntityClass() {
        return entityClass;
    }

    @Override
    public IType getPotatoType() {
        return potatoType;
    }

    @Override
    public Class<?> getIdClass() {
        return idClass;
    }

    @Override
    public Class<?> getMapperClass() {
        return mapperClass;
    }

    @Override
    public Class<?> getCriteriaBuilderClass() {
        return criteriaBuilderClass;
    }

    @Override
    public ICriteriaBuilder<?> newCriteriaBuilder() {
        if (criteriaBuilderClass == null)
            throw new NullPointerException("criteriaBuilderClass");
        try {
            return (ICriteriaBuilder<?>) criteriaBuilderClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalUsageException("can't instantiate " + criteriaBuilderClass, e);
        }
    }

    @Override
    public String[] getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }

    @Override
    public String[] getPrimaryKeyPropertyNames() {
        return primaryKeyPropertyNames;
    }

    @Override
    public IProperty getPrimaryKeyProperty(String name) {
        return primaryKeyProperties.get(name);
    }

    @Override
    public int getIdColumnCount() {
        if (primaryKeyColumns != null)
            return primaryKeyColumns.length;
        else
            return idClass == null ? 0 : 1;
    }

    @Override
    public Object[] parseIdColumns(String... columns)
            throws ParseException {
        if (idClass == null)
            throw new NoIdentifierException(entityClass.toString());
        if (columns.length < 1)
            throw new IllegalArgumentException("columns empty");

        Object[] values;
        if (primaryKeyPropertyNames == null) {
            values = new Object[1];
            try {
                Object val = StrVar.parse(idClass, columns[0]);
                values[0] = val;
            } catch (ParseException e) {
                String err = String.format("error parse id property[%d]: %s %s: value `%s`, in entity type %s", //
                        0, idClass.getName(), "ID", columns[0], entityClass.getName());
                throw new ParseException(err, e);
            }
        } else {
            int n = primaryKeyPropertyNames.length;
            values = new Object[n];
            for (int i = 0; i < n; i++) {
                String propertyName = primaryKeyPropertyNames[i];
                IProperty property = primaryKeyProperties.get(propertyName);
                Class<?> propertyType = property.getPropertyClass();
                try {
                    Object val = StrVar.parse(propertyType, columns[i]);
                    values[i] = val;
                } catch (ParseException e) {
                    String err = String.format("error parse id property[%d]: %s %s: value `%s`, in entity type %s", //
                            i, propertyType.getName(), property.getName(), columns[i], entityClass.getName());
                    throw new ParseException(err, e);
                }
            }
        }
        return values;
    }

    @Override
    public Object parseSimpleId(String str)
            throws ParseException {
        if (idClass == null)
            throw new NoIdentifierException(entityClass.toString());

        Object value;
        try {
            value = StrVar.parse(idClass, str);
        } catch (ParseException e) {
            String err = String.format("error parse id: %s from `%s`, in entity type %s", //
                    idClass.getName(), str, entityClass.getName());
            throw new ParseException(err, e);
        }
        return value;
    }

    @Override
    public Object newId(Object[] parameters)
            throws ReflectiveOperationException {
        if (parameters == null)
            throw new NullPointerException("parameters");

        int n = parameters.length;
        if (n == 1) {
            Object param = parameters[0];
            if (param == null)
                return null;
            Class<?> actual = param.getClass();
            Class<?> formal = idClass;
            if (formal.isPrimitive())
                formal = Primitives.box(formal);
            if (actual == formal)
                return param;
        }

        Class<?> atv[] = new Class<?>[n];
        for (int i = 0; i < n; i++)
            atv[i] = parameters[i] == null ? null : parameters[i].getClass();

        try {
            Constructor<?> ctor = idClass.getConstructor(atv);
            return ctor.newInstance(parameters);
        } catch (NoSuchMethodException e) {
            L: for (Constructor<?> ctor : idClass.getConstructors()) {
                Class<?>[] ftv = ctor.getParameterTypes();
                if (ftv.length != n)
                    continue;
                for (int i = 0; i < n; i++) {
                    Class<?> actual = atv[i];
                    if (actual == null)
                        continue;
                    Class<?> formal = ftv[i];
                    if (formal.isPrimitive())
                        formal = Primitives.box(formal);
                    if (! formal.isAssignableFrom(actual))
                        continue L;
                }
                // found the first match
                return ctor.newInstance(parameters);
            }
            throw e;
        }
    }

}