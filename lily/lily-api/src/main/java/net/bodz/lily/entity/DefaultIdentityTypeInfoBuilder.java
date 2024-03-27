package net.bodz.lily.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultIdentityTypeInfoBuilder {

    Class<? extends IIdentity> idClass;
    List<String> columnNames = new ArrayList<>();
    List<String> propertyNames = new ArrayList<>();

    public DefaultIdentityTypeInfoBuilder idClass(Class<? extends IIdentity> idClass) {
        this.idClass = idClass;
        return this;
    }

    public DefaultIdentityTypeInfoBuilder columnProperty(String columnName, String propertyName) {
        columnNames.add(columnName);
        propertyNames.add(propertyName);
        return this;
    }

    public DefaultIdentityTypeInfoBuilder columnNames(String... columnNames) {
        this.columnNames.addAll(Arrays.asList(columnNames));
        return this;
    }

    public DefaultIdentityTypeInfoBuilder propertyNames(String... propertyNames) {
        this.propertyNames.addAll(Arrays.asList(propertyNames));
        return this;
    }

    public DefaultIdentityTypeInfo build() {
        String[] cv = columnNames.toArray(new String[0]);
        String[] pv = propertyNames.toArray(new String[0]);
        return new DefaultIdentityTypeInfo(idClass, cv, pv);
    }

}
