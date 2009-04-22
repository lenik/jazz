package net.bodz.dist.ins;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.LogTerm;
import net.bodz.dist.ins.util.Flags;

public interface ISession {

    Project getProject();

    Component getComponent(String id);

    Scheme getScheme();

    void setScheme(String schemeName);

    void setScheme(Scheme scheme);

    UserInterface getUserInterface();

    LogTerm getLogger();

    int REBOOT = 1;

    Flags getFlags();

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
     * @return {@link Attachment} which can be opened later. After used the
     *         attachment, it can be left opened for next time use, all unclosed
     *         attachments are auto closed at the end of the session. The return
     *         value is never be <code>null</code>.
     * @throws IOException
     *             if the name is invalid or failed to get the attachment.
     */
    Attachment getAttachment(String name) throws IOException;

    /**
     * Close all attachments.
     */
    void closeAttachments();

    /**
     * Load and scatter registry data into components.
     */
    void loadRegistry() throws SessionException;

    /**
     * Gather and persist registry data from components.
     */
    void saveRegistry() throws SessionException;

    void dump(CharOut out);

}
