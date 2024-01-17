package net.bodz.violet.art.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class UOMCriteriaBuilder
        extends CoObjectCriteriaBuilder {

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
