package net.bodz.dist.ins;

import java.io.File;

import net.bodz.bas.gui.Interaction;

public interface ISession {

    IProject getProject();

    Interaction getInteraction();

    /**
     * @param pos
     *            position in the stack, 0 for current component, and 1 for
     *            parent, etc. The bottom of the stack should be the project.
     */
    IComponent getStack(int pos);

    /**
     * install/uninstall process should check this flag frequently to cancel
     * immediately.
     */
    boolean isCanceled();

    int REBOOT = 1;

    Flags getFlags();

    /**
     * set the progress of the current component.
     */
    void setComponentProgress(int progressIndex);

    void logFatal(Object... args);

    void logError(Object... args);

    void logWarn(Object... args);

    void logInfo(Object... args);

    void logDetail(Object... args);

    void logDebug(Object... args);

    Object get(String attr);

    void set(String attr, Object value);

    File getBaseDir(String name);

    void setBaseDir(String name, File dir);

    /**
     * @return should always be non-<code>null</code>
     */
    IAttachment getAttachment(String name);

    void dump(IComponent component, int pnum) throws InstallException;

    void install(IComponent component, int pnum) throws InstallException;

    void uninstall(IComponent component, int pnum) throws InstallException;

}
