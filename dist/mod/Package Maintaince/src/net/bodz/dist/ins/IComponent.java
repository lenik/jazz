package net.bodz.dist.ins;

import java.util.Collection;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

public interface IComponent {

    /**
     * name must be unique in the project.
     */
    String getName();

    ImageData getImage();

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

    int getSize();

    int getMoreSize();

    boolean isSelected();

    /**
     * @return <code>null</code> if none.
     */
    Collection<IComponent> getChildren();

    /**
     * @return component names this component required. <code>null</code> if
     *         none.
     */
    String[] getDependancy();

    boolean hasConfig();

    ConfigPage createConfig(ISession session, Composite parent, int style);

    /**
     * This method won't be called if the component doesn't have data.
     */
    void dump(ISession session) throws InstallException;

    /**
     * @param dumpedData
     *            <code>null</code> if no data.
     * @return <code>true</code> if component install succeeded, and should do
     *         the corresponding uninstall.
     */
    boolean install(ISession session) throws InstallException;

    void uninstall(ISession session) throws InstallException;

}
