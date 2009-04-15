package net.bodz.dist.ins;

import java.util.Collection;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

public interface IComponent {

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

    int getSize();

    int getMoreSize();

    /**
     * @return <code>null</code> if none.
     */
    Collection<IComponent> getChildren();

    /**
     * @return component names this component required. <code>null</code> if
     *         none.
     */
    Collection<IComponent> getDependancy();

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

    /**
     * This method won't be called if the component doesn't have data.
     */
    void pack(ISession session) throws InstallException;

    /**
     * @param dumpedData
     *            <code>null</code> if no data.
     * @return <code>true</code> if component install succeeded, and should do
     *         the corresponding uninstall.
     */
    boolean install(ISession session) throws InstallException;

    void uninstall(ISession session) throws InstallException;

}
