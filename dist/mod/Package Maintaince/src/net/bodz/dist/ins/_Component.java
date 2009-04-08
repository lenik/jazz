package net.bodz.dist.ins;

import java.util.Collection;

import net.bodz.bas.a.A_bas;

import org.eclipse.swt.widgets.Composite;

public abstract class _Component implements IComponent {

    private boolean visible;
    private boolean selected;

    public _Component(boolean visible, boolean defaultSelected) {
        this.visible = visible;
        this.selected = defaultSelected;
    }

    @Override
    public String getName() {
        String typeName = getClass().getName();
        return typeName;
    }

    @Override
    public String getCaption() {
        String caption = A_bas.getDisplayName(getClass());
        return caption;
    }

    @Override
    public String getDescription() {
        return getCaption();
    }

    @Override
    public boolean isFeature() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getMoreSize() {
        return 0;
    }

    @Override
    public boolean hasConfig() {
        return false;
    }

    @Override
    public ConfigPage createConfig(Composite parent, int style) {
        return null;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public Collection<IComponent> getChildren() {
        return null;
    }

    @Override
    public String[] getDependancy() {
        return null;
    }

    @Override
    public void enter(ISession session) {
    }

    @Override
    public void leave(ISession session) {
    }

    @Override
    public void dump(ISession session) throws InstallException {
    }

}
