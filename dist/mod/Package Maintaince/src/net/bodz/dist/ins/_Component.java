package net.bodz.dist.ins;

import java.util.Collection;

import net.bodz.bas.a.A_bas;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

public abstract class _Component implements IComponent {

    private String  id;

    private boolean visible;
    private boolean selection;

    public _Component(boolean visible, boolean defaultSelected) {
        this.visible = visible;
        this.selection = defaultSelected;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        String typeName = getClass().getName();
        return typeName;
    }

    @Override
    public ImageData getImage() {
        return null;
    }

    @Override
    public String getText() {
        String text = A_bas.getDisplayName(getClass());
        return text;
    }

    @Override
    public String getDoc() {
        return getText();
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
    public boolean getSelection() {
        return selection;
    }

    @Override
    public void setSelection(boolean selection) {
        this.selection = selection;
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
    public boolean hasConfig() {
        return false;
    }

    @Override
    public ConfigPage createConfig(ISession session, Composite parent, int style) {
        return null;
    }

    @Override
    public Object getData() {
        return null;
    }

    /**
     * The default implementation ignores the data.
     */
    @Override
    public void setData(Object data) {
    }

    @Override
    public void pack(ISession session) throws InstallException {
    }

    @Override
    public void uninstall(ISession session) throws InstallException {
    }

}
