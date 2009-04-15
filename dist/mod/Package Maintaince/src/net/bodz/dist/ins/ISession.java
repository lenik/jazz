package net.bodz.dist.ins;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.log.LogTerm;
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

    LogTerm getLogger();

    Object get(String attr);

    void set(String attr, Object value);

    File getBaseDir(String name);

    void setBaseDir(String name, File dir);

    ResFolder getResFolder();

    void setResFolder(ResFolder resFolder);

    void setResFolder(File dir);

    ResLink newResource(String resPath) throws IOException;

    ResLink findResource(String resPath) throws IOException;

    /**
     * @param name
     *            name of the attachment to use
     * @return {@link IAttachment} which can be opened later. After used the
     *         attachment, it can be left opened for next time use, all unclosed
     *         attachments are auto closed at the end of the session. The return
     *         value is never be <code>null</code>.
     * @throws IOException
     *             if the name is invalid or failed to get the attachment.
     */
    IAttachment getAttachment(String name) throws IOException;

    void pack() throws InstallException;

    void install() throws InstallException;

    void uninstall() throws InstallException;

}
