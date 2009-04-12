package net.bodz.dist.ins;

import java.io.File;

import net.bodz.bas.rt.Interaction;
import net.bodz.dist.ins.util.Flags;

public interface ISession {

    IProject getProject();

    IComponent getComponent(String id);

    Interaction getInteraction();

    /**
     * install/uninstall process should check this flag frequently to cancel
     * immediately.
     */
    boolean isCanceling();

    void cancel();

    void addCancelListener(CancelListener listener);

    void removeCancelListener(CancelListener listener);

    int REBOOT = 1;

    Flags getFlags();

    Progress getProgress();

    /** increase the progress */
    void logFatal(Object... args);

    /** increase the progress */
    void logError(Object... args);

    /** increase the progress */
    void logWarn(Object... args);

    /** increase the progress */
    void logInfo(Object... args);

    /** doesn't increase the progress */
    void logDetail(Object... args);

    /** doesn't increase the progress */
    void logDebug(Object... args);

    Object get(String attr);

    void set(String attr, Object value);

    File getBaseDir(String name);

    void setBaseDir(String name, File dir);

    ResourceUnion findResource(String resourcePath);

    /**
     * @return should always be non-<code>null</code>
     */
    IAttachment getAttachment(String name);

    void pack(IComponent component, int parentUnits) throws InstallException;

    void install(IComponent component, int parentUnits) throws InstallException;

    void uninstall(IComponent component, int parentUnits)
            throws InstallException;

}
