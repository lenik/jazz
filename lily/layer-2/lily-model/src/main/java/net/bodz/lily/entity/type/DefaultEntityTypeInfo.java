package net.bodz.lily.entity.type;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;
import net.bodz.lily.entity.IdFn;
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

        if (idClass != null) {
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

//            Comparator<ColumnProperty> order = new SourceDeclaredPropertyOrder(idClass);
            Arrays.sort(idProperties, ColumnPropertyOrder.INSTANCE);
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
        return idProperties.length;
    }

    @Override
    public Object[] parseIdColumns(String... columns)
            throws ParseException {
        if (idClass == null)
            throw new NoIdentifierException(entityClass.toString());

        Object[] values = new Object[idProperties.length];
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

}
