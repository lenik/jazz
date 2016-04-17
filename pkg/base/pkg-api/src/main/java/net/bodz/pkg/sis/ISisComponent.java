package net.bodz.pkg.sis;

import net.bodz.bas.fmt.rst.IRstSerializable;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.IDependencyAware;
import net.bodz.bas.t.event.IPropertyChangeSource;
import net.bodz.bas.t.tree.IMutableTreeNode;
import net.bodz.bas.ui.dom1.IUiElement;
import net.bodz.bas.ui.model.action.IProgressMonitor;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocElement;
import net.bodz.swt.c.pageflow.IPage;

public interface ISisComponent
        extends //
        IUiElement, //
        IXjdocElement, //
        IMutableTreeNode<ISisComponent>, //
        IDependencyAware<ISisComponent>, //
        IPropertyChangeSource, //
        IRstSerializable {

    ISisProject getProject();

    /**
     * id must be unique in the component tree.
     * 
     * @return <code>null</code> will set (by {@link #setId(String)}) to an auto generated unique
     *         id.
     */
    String getId();

    // String getName();

    boolean isSelected();

    void setSelected(boolean selected);

    boolean isLocked();

    void setLocked(boolean locked);

    boolean isVisible();

    void setVisible(boolean visible);

    IPage getConfigPage();

    boolean isShowConfig();

    void setShowConfig(boolean showConfig);

    // ________________________________________________________________________
    // ⇱ Part: Actions
    //

    long getSpaceRequired();

    int getWorks();

    void pack(IProgressMonitor monitor, IOptions options);

    void install(IProgressMonitor monitor, IOptions options);

    void remove(IProgressMonitor monitor, IOptions options);

}
