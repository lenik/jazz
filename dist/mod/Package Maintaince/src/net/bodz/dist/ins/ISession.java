package net.bodz.dist.ins;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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

    // XXX - ...
    void setLogger(LogTerm logger);

    int REBOOT = 1;

    Flags getFlags();

    void set(String variableName, Object variableValue);

    Object get(String variableName);

    /**
     * @throws IllegalArgumentException
     *             if variableName isn't defined.
     * @return never <code>null</code>
     */
    File getFile(String variableName);

    String expand(String s);

    List<ResFolder> getResFolders();

    boolean addResFolder(ResFolder resFolder);

    /**
     * @param beforeIndex
     *            set to 0 if you want to make the resFolder as default output.
     */
    void addResFolder(int beforeIndex, ResFolder resFolder);

    ResLink newResource(String resPath) throws IOException;

    /**
     * @throws NoSuchElementException
     *             if specified resource isn't existed.
     */
    ResLink findResource(String resPath, boolean autoCreate) throws IOException,
            NoSuchElementException;

    /**
     * Return attachment from pool, or get a new one.
     * 
     * @param name
     *            name of the attachment to use
     * @param autoCreate
     *            create new attachment resource if not existed
     * @return {@link Attachment} which can be opened later. After used the
     *         attachment, it can be left opened for next time use, all unclosed
     *         attachments are auto closed at the end of the session. The return
     *         value is never be <code>null</code>.
     * @throws IOException
     *             if the name is invalid or failed to get the attachment.
     */
    Attachment getAttachment(String name, boolean autoCreate) throws IOException;

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
