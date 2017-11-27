package net.bodz.violet.store.impl;

import net.bodz.lily.model.base.CoObjectMask;

public class UOMMask
        extends CoObjectMask {

    public String property;
    public boolean noProperty;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isNoProperty() {
        return noProperty;
    }

    public void setNoProperty(boolean noProperty) {
        this.noProperty = noProperty;
    }

}
