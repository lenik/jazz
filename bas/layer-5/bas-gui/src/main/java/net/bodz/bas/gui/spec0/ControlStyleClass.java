package net.bodz.bas.gui.spec0;

import net.bodz.bas.gui.css3.Css3StyleClass;

public class ControlStyleClass
        extends Css3StyleClass
        implements IControlStyleClass {

    public ControlStyleClass(IControlStyleClass parent) {
        super(parent);
    }

    @Override
    public IControlStyleClass getParent() {
        return (IControlStyleClass) super.getParent();
    }

    @Override
    public Boolean getEnabled() {
        return getBooleanProperty("enabled", getParent().getEnabled(), true);
    }

    @Override
    public void setEnabled(Boolean enabled) {
        setProperty("enabled", enabled);
    }

    @Override
    public Boolean getReadOnly() {
        return getBooleanProperty("read-only", getParent().getReadOnly(), false);
    }

    @Override
    public void setReadOnly(Boolean readOnly) {
        setProperty("read-only", readOnly);
    }

    @Override
    public Integer getTabOrder() {
        return getIntegerProperty("tab-order", getParent().getTabOrder(), false);
    }

    @Override
    public void setTabOrder(Integer tabOrder) {
        setProperty("tab-order", tabOrder);
    }

    @Override
    public String getTooltip() {
        return getProperty("tooltip");
    }

    @Override
    public void setTooltip(String tooltip) {
        setProperty("tooltip", tooltip);
    }

}
