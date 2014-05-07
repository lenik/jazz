package net.bodz.pkg.sis;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.action.IAction;
import net.bodz.bas.c.action.IProgressMonitor;
import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.source.ChainOrder;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.IDependencyAware;
import net.bodz.bas.t.event.IPropertyChangeListener;
import net.bodz.bas.t.event.IPropertyChangeSource;
import net.bodz.bas.t.tree.ITreeNode;
import net.bodz.bas.t.tree.MapTreeNode;
import net.bodz.bas.text.rst.ElementHandlerException;
import net.bodz.bas.text.rst.IElementHandler;
import net.bodz.bas.text.rst.IRstElement;
import net.bodz.bas.text.rst.IRstOutput;
import net.bodz.bas.text.rst.IRstSerializable;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;
import net.bodz.mda.xjdoc.ClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.swt.c.pageflow.IPage;

public abstract class AbstractSisComponent
        extends MapTreeNode<ISisComponent>
        implements ISisComponent, IElementHandler, II18nCapable {

    private static final long serialVersionUID = 1L;

    static final String CHILD_KEY_PREFIX = "c:";

    static final Logger logger = LoggerFactory.getLogger(AbstractSisComponent.class);

    private String name;
    private String id;

    private IElementDoc xjdoc;

    private boolean locked = false;
    private boolean selected = true;
    private boolean visible = true;
    private boolean showConfig = true;

    private Set<ISisComponent> dependencies = new HashSet<>();

    public AbstractSisComponent(ISisComponent parent, String namePrefix) {
        if (namePrefix == null)
            namePrefix = Strings.hyphenatize(getClass().getSimpleName());

        if (parent == null) {
            name = namePrefix;
            id = name;
        } else {
            name = TextMap.fn.searchUnusedKey(parent.childKeySet(), namePrefix);

            boolean parentIsRoot = parent.getParent() == null;
            String parentId = parent.getId();
            if (parentId == null || parentIsRoot)
                id = name;
            else
                id = parentId + '.' + name;

            attach(parent);
        }
    }

    @Override
    public IElementDoc getXjdoc() {
        if (xjdoc == null) {
            Class<?> clazz = getClass();
            ClassDoc classDoc = ClassDocLoader.load(clazz);

            if (classDoc == null) {
                logger.warn("No class doc available for " + clazz);
                classDoc = new ClassDoc(clazz.getName());
            }

            xjdoc = classDoc;
        }
        return xjdoc;
    }

    public void setXjdoc(IElementDoc xjdoc) {
        if (xjdoc == null)
            throw new NullPointerException("xjdoc");
        this.xjdoc = xjdoc;
    }

    /** ⇱ Implementation Of {@link ISisComponent}. */
    /* _____________________________ */static section.iface __COMPONENT__;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
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
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public IPage getConfigPage() {
        return null;
    }

    @Override
    public boolean isShowConfig() {
        return showConfig;
    }

    @Override
    public void setShowConfig(boolean showConfig) {
        this.showConfig = showConfig;
    }

    /** ⇱ Implementation Of {@link IElement}. */
    /* _____________________________ */static section.iface __ELEMENT__;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public iString getLabel() {
        return null;
    }

    @Override
    public iString getDescription() {
        return null;
    }

    @Override
    public iString getHelpDoc() {
        return null;
    }

    @Override
    public int getVerboseLevel() {
        return 0;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public IUiElementStyleDeclaration getStyle() {
        return null;
    }

    /** ⇱ Implementation Of {@link ITreeNode}. */
    /* _____________________________ */static section.iface __TREE_NODE__;

    @Override
    public ISisProject getProject() {
        return (ISisProject) getRoot();
    }

    @Override
    protected Map<String, ISisComponent> createMap() {
        return new LinkedHashMap<String, ISisComponent>();
    }

    /** ⇱ Implementation Of {@link IDependencyAware}. */
    /* _____________________________ */static section.iface __DEPENDENCY__;

    @Override
    public Set<ISisComponent> getDependencies() {
        if (dependencies == null)
            return Collections.emptySet();
        else
            return Collections.unmodifiableSet(dependencies);
    }

    @Override
    public Iterable<? extends ISisComponent> dependencyClosure() {
        return IDependencyAware.fn.<ISisComponent> dependencyClosure(this);
    }

    public void addDependancy(ISisComponent dependency) {
        dependencies.add(dependency);
    }

    public void removeDependency(ISisComponent dependency) {
        dependencies.remove(dependency);
    }

    /** ⇱ Implementation Of {@link IPropertyChangeSource}. */
    /* _____________________________ */static section.iface __PROPERTY_CHANGE__;

    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(IPropertyChangeListener listener) {
    }

    @Override
    public void addPropertyChangeListener(String propertyName, IPropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(IPropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(String propertyName, IPropertyChangeListener listener) {
    }

    /** ⇱ Implementation Of {@link IRstSerializable}. */
    /* _____________________________ */static section.iface __RST__;

    @OverrideOption(order = ChainOrder.TAIL)
    @Override
    public void writeObject(IRstOutput out)
            throws IOException {
        for (ISisComponent child : getChildren()) {
            out.beginElement(CHILD_KEY_PREFIX + child.getName());
            child.writeObject(out);
            out.endElement();
        }
    }

    @Override
    public IElementHandler getElementHandler() {
        return this;
    }

    @Override
    public boolean attribute(String name, String data)
            throws ParseException, ElementHandlerException {
        return false;
    }

    @Override
    public IElementHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * It's assumed attributes are processed in stream-like ways. And never buffered.
     */
    @Override
    public boolean endChild(IRstElement element)
            throws ElementHandlerException {
        return true;
    }

    @Override
    public void complete(IRstElement element)
            throws ElementHandlerException {
    }

    /** ⇱ Implementation Of {@link IAction}. */
    /* _____________________________ */static section.iface __section__;

    @Override
    public long getSpaceRequired() {
        return 0L;
    }

    @Override
    public int getWorks() {
        return 1;
    }

    @Override
    public void pack(IProgressMonitor monitor, IOptions options) {
        for (ISisComponent child : getChildren()) {
            monitor.enter(child.getName(), child.getWorks(), child.getWorks(), 0);
            child.pack(monitor, options);
            monitor.leave();
        }
    }

    @Override
    public void install(IProgressMonitor monitor, IOptions options) {
        for (ISisComponent child : getChildren()) {
            monitor.enter(child.getName(), child.getWorks(), child.getWorks(), 0);
            child.install(monitor, options);
            monitor.leave();
        }
    }

    @Override
    public void remove(IProgressMonitor monitor, IOptions options) {
        for (ISisComponent child : getChildren()) {
            monitor.enter(child.getName(), child.getWorks(), child.getWorks(), 0);
            child.remove(monitor, options);
            monitor.leave();
        }
    }

}
