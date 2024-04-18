package net.bodz.lily.criteria;

import java.util.List;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.lily.criterion.ITypeInferrer;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class ColumnAndThenProps
        implements
            ITypeInferrer {

    final IEntityTypeInfo typeInfo;

    public ColumnAndThenProps(IEntityTypeInfo typeInfo) {
        if (typeInfo == null)
            throw new NullPointerException("typeInfo");
        this.typeInfo = typeInfo;
    }

    @Override
    public Class<?> getFieldType(List<String> fieldNames) {
        if (fieldNames == null)
            throw new NullPointerException("fieldNames");
        if (fieldNames.isEmpty())
            throw new IllegalArgumentException("no field name");
        if (fieldNames.size() != 1)
            throw new NotImplementedException();

        // column-name, property-path
        String head = fieldNames.get(0);

        IProperty property = typeInfo.getPropertyForColumn(head);
        if (property == null)
            throw new IllegalArgumentException("invalid column name: " + head);

        Class<?> propertyType = property.getPropertyClass();
        return propertyType;
    }

}
