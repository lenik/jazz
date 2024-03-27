package net.bodz.lily.entity;

import net.bodz.bas.potato.element.IProperty;

public interface IIdentityTypeInfo {

    Class<? extends IIdentity> getIdentityClass();

    String[] getColumnNames();

    String[] getPropertyNames();

    IProperty getProperty(String name);

}
