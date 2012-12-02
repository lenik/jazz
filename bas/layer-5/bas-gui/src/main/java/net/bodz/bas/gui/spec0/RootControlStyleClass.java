package net.bodz.bas.gui.spec0;

import net.bodz.bas.gui.css3.RootCss3StyleClass;

public class RootControlStyleClass
        extends RootCss3StyleClass
        implements IControlStyleClass {

    private static final long serialVersionUID = 1L;

    Boolean enabled;
    Boolean readOnly;
    Integer tabOrder;
    String tooltip;

    @Override
    public IControlStyleClass getParent() {
        return (IControlStyleClass) super.getParent();
    }

    @Override
    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Boolean getReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public Integer getTabOrder() {
        return tabOrder;
    }

    @Override
    public void setTabOrder(Integer tabOrder) {
        this.tabOrder = tabOrder;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }

    @Override
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

}
