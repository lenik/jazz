package net.bodz.lily.entity.attachment;

import java.io.IOException;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.manager.IJdbcRowOpListener;
import net.bodz.lily.entity.manager.JdbcRowOpEvent;
import net.bodz.lily.entity.manager.JdbcRowOpType;

public class UpdateAttachments
        implements
            IJdbcRowOpListener {

    static final Logger logger = LoggerFactory.getLogger(UpdateAttachments.class);

    IId<?> idRef;
    IHaveAttachmentListing owner;

    public <T extends IId<?> & IHaveAttachmentListing> UpdateAttachments(T obj) {
        this.idRef = obj;
        this.owner = obj;
    }

    protected String getPreferredName(IAttachment item)
            throws IOException {
        String name = item.getName();
        if (name == null)
            return null;
        String ext = FilePath.getExtension(name, true);
        return item.getSHA1() + ext;
    }

    public void renameSubDirToId(IAttachment attachment, String id)
            throws IOException {
        if (attachment == null)
            throw new NullPointerException("attachment");
        String subDirName = attachment.getDirName();
        if (Nullables.equals(subDirName, "new"))
            attachment.moveTo(id, attachment.getFileName());
    }

    @Override
    public boolean beforeRowOperation(JdbcRowOpEvent event)
            throws Exception {
        if (event.getOpType() != JdbcRowOpType.UPDATE)
            return true;

        Object id = idRef.id();
        if (id != null) {
            String idStr = id.toString();
            IAttachmentListing listing = owner.getAttachmentListing();
            for (String category : listing.getAttachmentCategories()) {
                for (IAttachment attachment : listing.getAttachments(category)) {
                    renameSubDirToId(attachment, idStr);
                }
            }
        }
        return true;
    }

}
