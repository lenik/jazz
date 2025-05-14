package net.bodz.lily.entity;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.element.IProperty;

public interface IIdentityTypeInfo {

    Class<? extends IIdentity> getIdentityClass();

    String[] getColumnNames();

    String[] getPropertyNames();

    @NotNull
    IProperty getProperty(int index);

    default IProperty getProperty(@NotNull String propertyName) {
        String[] names = getPropertyNames();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(propertyName))
                return getProperty(i);
        }
        return null;
    }


}
