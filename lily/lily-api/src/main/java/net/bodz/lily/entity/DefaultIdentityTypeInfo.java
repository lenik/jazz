package net.bodz.lily.entity;

public class DefaultIdentityTypeInfo
        extends AbstractIdentityTypeInfo {

    final String[] columnNames;
    final String[] propertyNames;

    public DefaultIdentityTypeInfo(Class<? extends IIdentity> idClass, String[] columnNames, String[] propertyNames) {
        super(idClass);
        this.columnNames = columnNames;
        this.propertyNames = propertyNames;
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public String[] getPropertyNames() {
        return propertyNames;
    }

}
