package net.bodz.dist.ins;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ResLink;
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

    ResFolder getResFolder();

    void setResFolder(ResFolder resFolder);

    ResLink newResource(String resPath) throws IOException;

    ResLink findResource(String resPath) throws IOException;

    /**
     * @return should always be non-<code>null</code>
     */
    IAttachment getAttachment(String name) throws IOException;

    void pack() throws InstallException;

    void install() throws InstallException;

    void uninstall() throws InstallException;

}
