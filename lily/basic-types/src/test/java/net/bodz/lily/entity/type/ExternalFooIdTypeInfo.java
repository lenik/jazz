package net.bodz.lily.entity.type;

import net.bodz.lily.entity.AbstractIdentityTypeInfo;

public class ExternalFooIdTypeInfo
        extends AbstractIdentityTypeInfo {

    static final String[] columnNames = { "name", "age" };
    static final String[] propertyNames = { "name", "age" };

    public ExternalFooIdTypeInfo() {
        super(FooId.class);
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public String[] getPropertyNames() {
        return propertyNames;
    }

    public static final ExternalFooIdTypeInfo INSTANCE = new ExternalFooIdTypeInfo();

}
