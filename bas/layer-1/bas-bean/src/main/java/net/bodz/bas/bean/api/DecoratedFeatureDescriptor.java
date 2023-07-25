package net.bodz.bas.bean.api;

import java.util.Enumeration;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedFeatureDescriptor
        extends AbstractDecorator<IFeatureDescriptor>
        implements
            IFeatureDescriptor {

    private static final long serialVersionUID = 1L;

    public DecoratedFeatureDescriptor(IFeatureDescriptor _orig) {
        super(_orig);
    }

    @Override
    public String getName() {
        return getWrapped().getName();
    }

    @Override
    public void setName(String name) {
        getWrapped().setName(name);
    }

    @Override
    public String getDisplayName() {
        return getWrapped().getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName) {
        getWrapped().setDisplayName(displayName);
    }

    @Override
    public Object getValue(String attributeName) {
        return getWrapped().getValue(attributeName);
    }

    @Override
    public void setValue(String attributeName, Object value) {
        getWrapped().setValue(attributeName, value);
    }

    @Override
    public String getShortDescription() {
        return getWrapped().getShortDescription();
    }

    @Override
    public void setShortDescription(String text) {
        getWrapped().setShortDescription(text);
    }

    @Override
    public boolean isPreferred() {
        return getWrapped().isPreferred();
    }

    @Override
    public void setPreferred(boolean preferred) {
        getWrapped().setPreferred(preferred);
    }

    @Override
    public boolean isHidden() {
        return getWrapped().isHidden();
    }

    @Override
    public void setHidden(boolean hidden) {
        getWrapped().setHidden(hidden);
    }

    @Override
    public boolean isExpert() {
        return getWrapped().isExpert();
    }

    @Override
    public void setExpert(boolean expert) {
        getWrapped().setExpert(expert);
    }

    @Override
    public Enumeration<String> attributeNames() {
        return getWrapped().attributeNames();
    }

}
