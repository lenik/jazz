package net.bodz.lily.entity.type;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.entity.Identifier;
import net.bodz.lily.model.base.CoObjectMask;

public class DefaultEntityTypeInfo
        implements
            IEntityTypeInfo {

    Class<?> entityClass;
    Class<?> idClass;
    Class<?> mapperClass;
    Class<?> criteriaClass;

    ColumnProperty[] idProperties;

    public DefaultEntityTypeInfo(Class<?> entityClass) {
        this.entityClass = entityClass;
        idClass = IdFn._getIdType(entityClass);
        mapperClass = IMapper.fn.getMapperClass(entityClass);
        criteriaClass = CoObjectMask.findMaskClass(entityClass);

        if (idClass != null && idClass.isAnnotationPresent(Identifier.class)) {
            BeanInfo beanInfo;
            try {
                beanInfo = Introspector.getBeanInfo(idClass);
            } catch (IntrospectionException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
            List<ColumnProperty> columns = new ArrayList<>();
            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                ColumnProperty property = new ColumnProperty(pd);
                if (property.getColumn() != null)
                    columns.add(property);
            }
            int n = columns.size();
            idProperties = new ColumnProperty[n];
            for (int i = 0; i < n; i++)
                idProperties[i] = columns.get(i);

            Comparator<ColumnProperty> order;
            order = new SourceDeclaredPropertyOrder(idClass);
            order = ColumnPropertyOrder.INSTANCE;
            Arrays.sort(idProperties, order);
        } // idClass != null
    }

    @Override
    public Class<?> getEntityClass() {
        return entityClass;
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
    public Class<?> getCrtieriaClass() {
        return criteriaClass;
    }

    @Override
    public int getIdColumnCount() {
        if (idProperties != null)
            return idProperties.length;
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
        if (idProperties == null) {
            values = new Object[1];
            try {
                Object val = parseValue(idClass, columns[0]);
                values[0] = val;
            } catch (ParseException e) {
                String err = String.format("error parse id property[%d]: %s %s: value `%s`, in entity type %s", //
                        0, idClass.getName(), "ID", columns[0], entityClass.getName());
                throw new ParseException(err, e);
            }
        } else {
            values = new Object[idProperties.length];
            for (int i = 0; i < idProperties.length; i++) {
                ColumnProperty property = idProperties[i];
                Class<?> propertyType = property.getPropertyType();
                try {
                    Object val = parseValue(propertyType, columns[i]);
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
    public Object parseId(String str)
            throws ParseException {
        if (idClass == null)
            throw new NoIdentifierException(entityClass.toString());

        Object value;
        try {
            value = parseValue(idClass, str);
        } catch (ParseException e) {
            String err = String.format("error parse id: %s from `%s`, in entity type %s", //
                    idClass.getName(), str, entityClass.getName());
            throw new ParseException(err, e);
        }
        return value;
    }

    static IVarConverter<String> strConv = VarConverters.getConverter(String.class);

    static <K> K parseValue(Class<K> valueType, String valueStr)
            throws ParseException {
        try {
            return strConv.to(valueStr, valueType);
        } catch (TypeConvertException e) {
            throw new ParseException("failed to parse: " + e.getMessage(), e);
        }
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
                    if (!formal.isAssignableFrom(actual))
                        continue L;
                }
                // found the first match
                return ctor.newInstance(parameters);
            }
            throw e;
        }
    }

}