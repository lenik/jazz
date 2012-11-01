package net.bodz.redist.installer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.graphics.ImageData;

import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.mda.tagbook.ArtifactDoc;
import net.bodz.mda.xjdoc.conv.ClassDocs;

public abstract class AbstractComponent
        implements IComponent, II18nCapable {

    private String id;
    private Object viewData;
    private Object registryData;

    private ImageData image;
    private String name;
    private String text;
    private String doc;

    private boolean enabled = true;
    private boolean visible;
    private boolean readOnly = false;
    private boolean selection;
    private long size;
    private long moreSize;

    private List<IComponent> children;
    private Set<IComponent> dependancy;

    public AbstractComponent(boolean visible, boolean defaultSelection) {
        Class<?> clazz = getClass();
        ArtifactDoc artifactDoc = ClassDocs.loadFromResource(clazz).as(ArtifactDoc.class);

        name = clazz.getSimpleName();
        text = artifactDoc.getLabel().toString();
        doc = artifactDoc.getText().toString();
        if (doc == null)
            doc = text;
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

    public long getSize() {
        return size;
    }

    protected void setSize(long size) {
        this.size = size;
    }

    public long getMoreSize() {
        return moreSize;
    }

    protected void setMoreSize(long moreSize) {
        this.moreSize = moreSize;
    }

    @Override
    public List<IComponent> getChildren() {
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

    @Override
    public Set<IComponent> getDependancy() {
        if (dependancy == null)
            return Collections.EMPTY_SET;
        return dependancy;
    }

    public void addDependancy(IComponent dependant) {
        if (dependancy == null)
            dependancy = new HashSet<IComponent>();
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
    public ConfigPage createConfig(ISession session) {
        return null;
    }

    /**
     * @return slightly bigger then a single progress index (1.00), in default implementation.
     */
    @Override
    public double getProgressScaleToParent() {
        return 1.01;
    }

    static class JobConcat
            extends SessionJob {

        public JobConcat(ISession session, IComponent component) {
            super(session, component);
        }

        @Override
        protected void runTree() {
            List<? extends SessionJob> list = getChildren();
            if (list == null || list.isEmpty())
                return;
            int jobCount = list.size();
            setProgress(0, jobCount);
            double progressIndex = 0;
            for (SessionJob job : list) {
                if (!moveOn(progressIndex, job.getDescription()))
                    return;
                job.run();
                progressIndex += job.progressIncrement;
            }
            setProgressIndex(jobCount);
        }

        @Override
        protected void _run() {
        }

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
            throw new IllegalArgumentException(tr._("Invalid type: ") + type);
        }

        List<IComponent> children = getChildren();
        if (children == null || children.isEmpty())
            return job;

        JobConcat concat = new JobConcat(session, this);
        List<SessionJob> q = new ArrayList<SessionJob>();
        double psum = 0;
        if (job != null) {
            q.add(job);
            psum += 1;
        }

        Scheme scheme = session.getScheme();
        for (IComponent child : children) {
            switch (type) {
            case PACK:
                break;
            case INSTALL:
            case UNINSTALL:
                if (!scheme.isIncluded(child))
                    continue;
            }
            SessionJob childJob = child.execute(type, session);
            if (childJob == null)
                continue;
            q.add(childJob);
            psum += child.getProgressScaleToParent();
        }
        if (q.isEmpty())
            return job;
        // if (q.size() == 1)
        // return q.get(0);

        for (SessionJob childJob : q) {
            double progressIncrement;
            IComponent child = childJob.getComponent();
            double c = child.getProgressScaleToParent();
            if (psum == 0) {
                progressIncrement = 1.0; // / q.size();
            } else
                progressIncrement = q.size() * c / psum;
            // this make the offset
            childJob.progressIncrement = progressIncrement;
            // this make the scale
            concat.addChildJob(childJob, progressIncrement);
        }
        return concat;
    }

    protected abstract class CJob
            extends SessionJob {

        public CJob(ISession session) {
            super(session, AbstractComponent.this);
        }

    }

    protected CJob pack(ISession session) {
        return null;
    }

    protected abstract CJob install(ISession session);

    protected CJob uninstall(ISession session) {
        return null;
    }

}
