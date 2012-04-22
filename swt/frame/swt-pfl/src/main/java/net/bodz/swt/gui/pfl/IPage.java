package net.bodz.swt.gui.pfl;

import java.beans.PropertyChangeListener;
import java.util.Collection;

import net.bodz.bas.collection.tree.TreePath;
import net.bodz.swt.err.ValidateException;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Control;

public interface IPage {

    String getPageTitle();

    ImageData getPageIcon();

    /**
     * @return <code>null</code> for non-page, which doesn't make history record. For non-page,
     *         {@link #service(ServiceContext)} must returns non-<code>null</code> path for
     *         redirection.
     */
    Control createUI(PageContainer pageContainer)
            throws PageException;

    /**
     * Be called only once for any instance.
     * 
     * @return <code>null</code> stay with-in current page, otherwise redirect to the returned path
     *         if any.
     */
    TreePath service(ServiceContext context)
            throws PageException;

    void enter(TreePath prev)
            throws PageException;

    void leave(TreePath next)
            throws PageException;

    /**
     * The page is sticked and leave is forbidden.
     */
    boolean isSticked();

    /**
     * Can't go ahead (or submit) if validation is failed.
     * 
     * @throws ValidateException
     */
    void validate()
            throws PageException, ValidateException;

    /**
     * @return <code>null</code> to use a default submit method.
     */
    Collection<PageMethod> getMethods();

    String PROP_PAGETITLE = "pageTitle";
    String PROP_PAGEICON = "pageIcon";
    String PROP_METHODS = "methods";
    String PROP_STICKED = "sticked";

    void addPropertyChangeListener(PropertyChangeListener listener);

    void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

}
