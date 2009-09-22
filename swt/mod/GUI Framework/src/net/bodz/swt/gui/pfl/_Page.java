package net.bodz.swt.gui.pfl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.types.TreePath;
import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.gui.a.A_gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class _Page implements Page {

    protected PageContainer         pageContainer;
    protected Composite             pageHolder;

    private boolean                 sticked;
    private List<PageMethod>        methods;
    protected PropertyChangeSupport propertyChangeSupport;

    public _Page() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public String getPageTitle() {
        Class<?> clazz = getClass();
        String doc = A_bas.getDoc(clazz);
        if (doc != null)
            return doc;
        return clazz.getName();
    }

    @Override
    public ImageData getPageIcon() {
        Class<?> clazz = getClass();
        ImageData icon = A_gui.getIcon(clazz);
        return icon;
    }

    @Override
    public Control createUI(PageContainer pageContainer) throws PageException {
        Composite pageHolder = new Composite(pageContainer, SWT.NONE);
        this.pageContainer = pageContainer;
        this.pageHolder = pageHolder;
        createContents(pageHolder);
        return pageHolder;
    }

    protected void createContents(Composite holder) throws PageException {
    }

    @Override
    public TreePath service(ServiceContext context) throws PageException {
        return null;
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void enter(TreePath prev) throws PageException {
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void leave(TreePath next) throws PageException {
    }

    protected void setSticked(boolean sticked) {
        if (this.sticked != sticked) {
            boolean old = this.sticked;
            this.sticked = sticked;
            propertyChangeSupport.firePropertyChange(Page.PROP_STICKED, old, sticked);
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
    public void validate() throws PageException, ValidateException {
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

    protected void setMethods(Collection<PageMethod> methods) {
        assert methods != null : "null methods";
        List<PageMethod> old = this.methods;
        this.methods = new ArrayList<PageMethod>(methods);
        propertyChangeSupport.firePropertyChange(PROP_METHODS, old, methods);
    }

    protected void setMethods(PageMethod... methods) {
        setMethods(Arrays.asList(methods));
    }

    protected void addMethod(PageMethod method) {
        assert method != null : "null method";
        List<PageMethod> old = this.methods;
        if (methods == null)
            methods = new ArrayList<PageMethod>();
        methods.add(method);
        propertyChangeSupport.firePropertyChange(PROP_METHODS, old, methods);
    }

    protected void removeMethod(PageMethod method) {
        if (methods.remove(method))
            propertyChangeSupport.firePropertyChange(PROP_METHODS, methods, methods);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

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
