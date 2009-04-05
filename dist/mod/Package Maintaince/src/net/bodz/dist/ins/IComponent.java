package net.bodz.dist.ins;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import org.eclipse.swt.widgets.Composite;

public interface IComponent {

    /**
     * name must be unique in the project.
     */
    String getName();

    String getCaption();

    String getDescription();

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

    boolean hasConfig();

    ConfigPage createConfig(Composite parent, int style);

    boolean isSelected();

    void setSelected(boolean selected);

    /**
     * @return <code>null</code> if none.
     */
    Collection<IComponent> getChildren();

    /**
     * @return component names this component required. <code>null</code> if
     *         none.
     */
    String[] getDependancy();

    boolean hasData();

    /**
     * This method won't be called if the component doesn't have data.
     */
    void dump(OutputStream dataOut) throws InstallException;

    /**
     * @param dumpedData
     *            <code>null</code> if no data.
     * @return <code>true</code> if component install succeeded, and should do
     *         the corresponding uninstall.
     */
    boolean install(InputStream dumpedData) throws InstallException;

    void uninstall() throws InstallException;

}
