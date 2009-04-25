package net.bodz.dist.ins;

import java.util.List;
import java.util.Set;

import net.bodz.bas.types.TreeNode;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

public interface Component extends TreeNode<Component> {

    /**
     * <code>null</code> id and duplicated id must be filled with a unique one,
     * with {@link #setId(String)}.
     */
    String getId();

    /**
     * This is mostly called by unique-id generators.
     */
    void setId(String id);

    ImageData getImage();

    String getName();

    String getText();

    String getDoc();

    /**
     * Change the selection state of feature component will cause refresh all
     * the sibling nodes.
     */
    boolean isFeature();

    boolean isEnabled();

    boolean isVisible();

    boolean isReadOnly();

    /** Initial selection */
    boolean getSelection();

    /** Instance variable */
    void setSelection(boolean selection);

    long getSize();

    long getMoreSize();

    /**
     * @return <code>null</code> if none.
     */
    @Override
    List<? extends Component> getChildren();

    /**
     * @return component names this component required. <code>null</code> if
     *         none.
     */
    Set<Component> getDependancy();

    /**
     * called by component tree viewer
     */
    Object getViewData();

    /**
     * called by component tree viewer
     */
    void setViewData(Object viewData);

    /**
     * called by packager, after {@link #pack(ISession)}, the data is then
     * persisted.
     */
    Object getRegistryData();

    /**
     * called by installer/uninstaller, before {@link #install(ISession)} and
     * {@link #uninstall(ISession)}
     */
    void setRegistryData(Object data);

    boolean hasConfig();

    ConfigPage createConfig(ISession session, Composite parent, int style);

    double getProgressScaleToParent();

    int PACK      = 0;
    int INSTALL   = 1;
    int UNINSTALL = 2;

    SessionJob execute(int type, ISession session);

}
