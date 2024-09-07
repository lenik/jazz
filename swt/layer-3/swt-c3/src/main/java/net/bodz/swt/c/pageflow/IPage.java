package net.bodz.swt.c.pageflow;

import java.util.Collection;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.bean.api.IPropertyChangeListener;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.bas.ui.err.UiValidationException;

public interface IPage {

    String getPageTitle();

    ImageData getPageIcon();

    /**
     * @return <code>null</code> for non-page, which doesn't make history record. For non-page,
     *         {@link #service(ServiceContext)} must returns non-<code>null</code> path for
     *         redirection.
     */
    Control createUI(PageContainer pageContainer);

    /**
     * Be called only once for any instance.
     *
     * @return <code>null</code> stay with-in current page, otherwise redirect to the returned path
     *         if any.
     */
    PathEntries service(ServiceContext context)
            throws PageException;

    void enter(PathEntries prev)
            throws PageException;

    void leave(PathEntries next)
            throws PageException;

    /**
     * The page is sticked and leave is forbidden.
     */
    boolean isSticked();

    /**
     * Can't go ahead (or submit) if validation is failed.
     */
    void validate()
            throws PageException, UiValidationException;

    /**
     * @return <code>null</code> to use a default submit method.
     */
    Collection<PageMethod> getMethods();

    String PROP_PAGETITLE = "pageTitle";
    String PROP_PAGEICON = "pageIcon";
    String PROP_METHODS = "methods";
    String PROP_STICKED = "sticked";

    void addPropertyChangeListener(IPropertyChangeListener listener);

    void addPropertyChangeListener(String propertyName, IPropertyChangeListener listener);

    void removePropertyChangeListener(IPropertyChangeListener listener);

    void removePropertyChangeListener(String propertyName, IPropertyChangeListener listener);

}
