package net.bodz.dist.ins;

import java.io.OutputStream;
import java.util.Collection;

import net.bodz.bas.log.ALog;

import org.eclipse.swt.widgets.Composite;

public abstract class _Component implements IComponent {

    protected final _Project project;
    protected final ALog     L;

    private boolean          visible;
    private boolean          selected;

    public _Component(_Project project, boolean visible, boolean defaultSelected) {
        this.project = project;
        this.L = new ALog();
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
        String simpleName = getClass().getSimpleName();
        return simpleName;
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
    public boolean hasData() {
        return false;
    }

    @Override
    public void dump(OutputStream dataOut) throws InstallException {
    }

}
