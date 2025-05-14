package net.bodz.lily.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.bean.BeanTypeProvider;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class BeanIdentityTypeInfo
        implements IIdentityTypeInfo {

    final Class<? extends IIdentity> idClass;

    Map<String, IProperty> propertyMap = new LinkedHashMap<>();
    IProperty[] properties;
    Column[] columns;
    String[] propertyNames;
    String[] columnNames;

    public BeanIdentityTypeInfo(Class<? extends IIdentity> idClass) {
        this.idClass = idClass;

        IType type = BeanTypeProvider.getInstance().getType(idClass);
        List<IProperty> propertyList = new ArrayList<>();
        for (IProperty property : type.getProperties()) {
            Column aColumn = property.getAnnotation(Column.class);
            if (aColumn == null)
                continue;
            propertyList.add(property);
            propertyMap.put(property.getName(), property);
        }

        propertyList.sort(new AbstractNonNullComparator<IProperty>() {
            @Override
            public int compareNonNull(IProperty o1, IProperty o2) {
                Ordinal aOrdinal1 = o1.getAnnotation(Ordinal.class);
                Ordinal aOrdinal2 = o2.getAnnotation(Ordinal.class);
                int ordinal1 = aOrdinal1 == null ? 0 : aOrdinal1.value();
                int ordinal2 = aOrdinal2 == null ? 0 : aOrdinal2.value();
                int cmp = ordinal1 - ordinal2;
                if (cmp != 0)
                    return cmp;
                return -1;
            }
        });
        properties = propertyList.toArray(new IProperty[0]);
        propertyNames = new String[properties.length];
        columns = new Column[properties.length];
        columnNames = new String[columns.length];

        for (int i = 0; i < properties.length; i++) {
            String propertyName = this.properties[i].getName();
            propertyNames[i] = propertyName;

            Column aColumn = properties[i].getAnnotation(Column.class);
            columns[i] = aColumn;

            String columnName = aColumn.name();
            if (columnName.isEmpty())
                columnName = StringId.UL.breakCamel(propertyName).toLowerCase();
            columnNames[i] = columnName;
        }
    }

    @Override
    public Class<? extends IIdentity> getIdentityClass() {
        return idClass;
    }

    public Map<String, IProperty> getPropertyMap() {
        return propertyMap;
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public String[] getPropertyNames() {
        return propertyNames;
    }

    @NotNull
    @Override
    public IProperty getProperty(int index) {
        String propertyName = propertyNames[index];
        return getProperty(propertyName);
    }

    @Override
    public IProperty getProperty(@NotNull String name) {
        return propertyMap.get(name);
    }

}
