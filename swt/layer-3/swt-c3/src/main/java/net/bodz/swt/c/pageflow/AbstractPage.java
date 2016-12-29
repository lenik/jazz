package net.bodz.swt.c.pageflow;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.bas.ui.err.UiValidationException;
import net.bodz.bas.ui.style.IImageData;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;
import net.bodz.bas.ui.style.ImageUsage;
import net.bodz.bas.ui.xjdoc.UiElementDoc;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public abstract class AbstractPage
        implements IPage, II18nCapable {

    ClassDoc classDoc;

    protected PageContainer pageContainer;
    protected Composite parent;

    private boolean sticked;
    private List<PageMethod> methods;
    protected PropertyChangeSupport propertyChangeSupport;

    public AbstractPage() {
        classDoc = Xjdocs.getDefaultProvider().getClassDoc(getClass());
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public String getPageTitle() {
        String header = classDoc.getText().getHeadPar();
        if (!Nullables.isEmpty(header))
            return header;

        // Default page title is the class name.
        Class<?> clazz = getClass();
        return clazz.getCanonicalName();
    }

    @Override
    public ImageData getPageIcon() {
        UiElementDoc gdoc = classDoc.to(UiElementDoc.class);
        IUiElementStyleDeclaration style = gdoc.getStyleClass();
        if (style != null) {
            IImageData image = style.getImage(ImageUsage.NORMAL);
            // TODO Convert to SWT image data...
        }
        return null;
    }

    @Override
    public synchronized Control createUI(PageContainer pageContainer)
// throws PageException
    {
        if (parent == null) {
            parent = new Composite(pageContainer, SWT.NONE);
            this.pageContainer = pageContainer;
            createContents(parent);
        }
        return parent;
    }

    protected abstract void createContents(Composite holder);

    @Override
    public PathEntries service(ServiceContext context)
            throws PageException {
        return null;
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void enter(PathEntries prev)
            throws PageException {
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void leave(PathEntries next)
            throws PageException {
    }

    protected void setSticked(boolean sticked) {
        if (this.sticked != sticked) {
            boolean old = this.sticked;
            this.sticked = sticked;
            propertyChangeSupport.firePropertyChange(IPage.PROP_STICKED, old, sticked);
        }
    }

    /**
     * @return <code>false</code> by default.
     */
    @Override
    public boolean isSticked() {
        return sticked;
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void validate()
            throws PageException, UiValidationException {
    }

    /**
     * default no methods
     */
    @Override
    public List<PageMethod> getMethods() {
        if (methods == null)
            return null;
        return Collections.unmodifiableList(methods);
    }

    public void setMethods(Collection<PageMethod> methods) {
        assert methods != null : "null methods";
        List<PageMethod> old = this.methods;
        this.methods = new ArrayList<PageMethod>(methods);
        propertyChangeSupport.firePropertyChange(PROP_METHODS, old, methods);
    }

    public void setMethods(PageMethod... methods) {
        setMethods(Arrays.asList(methods));
    }

    public void addMethod(PageMethod method) {
        assert method != null : "null method";
        List<PageMethod> old = this.methods;
        if (methods == null)
            methods = new ArrayList<PageMethod>();
        methods.add(method);
        propertyChangeSupport.firePropertyChange(PROP_METHODS, old, methods);
    }

    public void removeMethod(PageMethod method) {
        if (methods.remove(method))
            propertyChangeSupport.firePropertyChange(PROP_METHODS, methods, methods);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getPageTitle();
    }

}
