package net.bodz.dist.ins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.a.A_bas;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

public abstract class _Component implements IComponent {

    private String           id;
    private Object           registryData;

    private ImageData        image;
    private String           name;
    private String           text;
    private String           doc;

    private boolean          enabled  = true;
    private boolean          visible;
    private boolean          readOnly = false;
    private boolean          selection;
    private int              size;
    private int              moreSize;

    private List<IComponent> children;
    private Set<IComponent>  dependancy;

    public _Component(boolean visible, boolean defaultSelection) {
        this.name = getClass().getName();
        this.text = A_bas.getDisplayName(getClass());
        this.doc = text;
        this.visible = visible;
        this.selection = defaultSelection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImageData getImage() {
        return image;
    }

    protected void setImage(ImageData image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    protected void setText(String text) {
        this.text = text;
    }

    public String getDoc() {
        return doc;
    }

    protected void setDoc(String doc) {
        this.doc = doc;
    }

    @Override
    public boolean isFeature() {
        return false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    protected void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isVisible() {
        return visible;
    }

    protected void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    protected void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean getSelection() {
        return selection;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    public int getSize() {
        return size;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    public int getMoreSize() {
        return moreSize;
    }

    protected void setMoreSize(int moreSize) {
        this.moreSize = moreSize;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<IComponent> getChildren() {
        if (children == null)
            return Collections.EMPTY_LIST;
        return children;
    }

    public void add(IComponent child) {
        if (children == null)
            children = new ArrayList<IComponent>();
        children.add(child);
    }

    public void remove(IComponent child) {
        if (children != null)
            children.remove(child);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<IComponent> getDependancy() {
        if (dependancy == null)
            return Collections.EMPTY_LIST;
        return dependancy;
    }

    public void addDependancy(IComponent dependant) {
        if (dependancy == null)
            dependancy = new HashSet<IComponent>();
        dependancy.add(dependant);
    }

    @Override
    public Object getRegistryData() {
        return registryData;
    }

    @Override
    public void setRegistryData(Object registryData) {
        this.registryData = registryData;
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
    public void pack(ISession session) throws InstallException {
    }

    @Override
    public void uninstall(ISession session) throws InstallException {
    }

}
