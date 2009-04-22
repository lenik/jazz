package net.bodz.dist.ins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.a.A_bas;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

public abstract class _Component implements Component {

    private String          id;
    private Object          viewData;
    private Object          registryData;

    private ImageData       image;
    private String          name;
    private String          text;
    private String          doc;

    private boolean         enabled  = true;
    private boolean         visible;
    private boolean         readOnly = false;
    private boolean         selection;
    private int             size;
    private int             moreSize;

    private List<Component> children;
    private Set<Component>  dependancy;

    public _Component(boolean visible, boolean defaultSelection) {
        this.name = getClass().getSimpleName();
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
    public List<Component> getChildren() {
        if (children == null)
            return Collections.EMPTY_LIST;
        return children;
    }

    public void add(Component child) {
        if (children == null)
            children = new ArrayList<Component>();
        children.add(child);
    }

    public void remove(Component child) {
        if (children != null)
            children.remove(child);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Component> getDependancy() {
        if (dependancy == null)
            return Collections.EMPTY_SET;
        return dependancy;
    }

    public void addDependancy(Component dependant) {
        if (dependancy == null)
            dependancy = new HashSet<Component>();
        dependancy.add(dependant);
    }

    public Object getViewData() {
        return viewData;
    }

    public void setViewData(Object viewData) {
        this.viewData = viewData;
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
    public final SessionJob execute(int type, ISession session) {
        SessionJob job;
        switch (type) {
        case PACK:
            job = pack(session);
            break;
        case INSTALL:
            job = install(session);
            break;
        case UNINSTALL:
            job = uninstall(session);
            break;
        default:
            throw new IllegalArgumentException("Invalid type: " + type);
        }
        List<Component> children = getChildren();
        if (children == null || children.isEmpty())
            return job;
        else {
            if (job == null)
                job = new SessionJob(session) {
                    @Override
                    protected void _run() {
                    }
                };
            Scheme scheme = session.getScheme();
            for (Component child : children) {
                if (!scheme.isIncluded(child))
                    continue;
                SessionJob childJob = child.execute(type, session);
                if (childJob != null)
                    job.addChildJob(childJob, 0);
            }
        }
        return job;
    }

    protected SessionJob pack(ISession session) {
        return null;
    }

    protected abstract SessionJob install(ISession session);

    protected SessionJob uninstall(ISession session) {
        return null;
    }

}
