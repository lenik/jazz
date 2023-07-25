package net.bodz.bas.bean.api;

import java.util.Enumeration;

public class NullFeatureDescriptor
        implements
            IFeatureDescriptor {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public void setDisplayName(String displayName) {
    }

    @Override
    public Object getValue(String attributeName) {
        return null;
    }

    @Override
    public void setValue(String attributeName, Object value) {
    }

    @Override
    public String getShortDescription() {
        return null;
    }

    @Override
    public void setShortDescription(String text) {
    }

    @Override
    public boolean isPreferred() {
        return false;
    }

    @Override
    public void setPreferred(boolean preferred) {
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public void setHidden(boolean hidden) {
    }

    @Override
    public boolean isExpert() {
        return false;
    }

    @Override
    public void setExpert(boolean expert) {
    }

    @Override
    public Enumeration<String> attributeNames() {
        return null;
    }

}
