package net.bodz.redist.installer;

import java.util.List;
import java.util.Set;

import org.eclipse.swt.graphics.ImageData;

import net.bodz.bas.collection.tree.TreeNode;

public interface IComponent
        extends TreeNode<IComponent> {

    /**
     * id must be unique in the component tree.
     * 
     * @return <code>null</code> will set (by {@link #setId(String)}) to an auto generated unique
     *         id.
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
     * Change the selection state of feature component will cause refresh all the sibling nodes.
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
    List<? extends IComponent> getChildren();

    /**
     * @return component names this component required. <code>null</code> if none.
     */
    Set<IComponent> getDependancy();

    /**
     * called by component tree viewer
     */
    Object getViewData();

    /**
     * called by component tree viewer
     */
    void setViewData(Object viewData);

    /**
     * called by packager, after {@link #pack(ISession)}, the data is then persisted.
     */
    Object getRegistryData();

    /**
     * called by installer/uninstaller, before {@link #install(ISession)} and
     * {@link #uninstall(ISession)}
     */
    void setRegistryData(Object data);

    boolean hasConfig();

    ConfigPage createConfig(ISession session);

    double getProgressScaleToParent();

    int PACK = 0;
    int INSTALL = 1;
    int UNINSTALL = 2;

    SessionJob execute(int type, ISession session);

}
