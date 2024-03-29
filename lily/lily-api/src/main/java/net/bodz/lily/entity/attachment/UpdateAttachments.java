package net.bodz.lily.entity.attachment;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.manager.IJdbcRowOpListener;
import net.bodz.lily.entity.manager.JdbcRowOpEvent;
import net.bodz.lily.entity.manager.JdbcRowOpType;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeItem;

public class UpdateAttachments
        implements
            IJdbcRowOpListener {

    static final Logger logger = LoggerFactory.getLogger(UpdateAttachments.class);

    IId<?> idRef;
    IHaveAttachments owner;

    public <T extends IId<?> & IHaveAttachments> UpdateAttachments(T obj) {
        this.idRef = obj;
        this.owner = obj;
    }

    protected String getPreferredName(IAttachment item)
            throws IOException {
        String name = item.getName();
        if (name == null)
            return null;
        String ext = FilePath.getExtension(name, true);
        return item.getFileSHA1() + ext;
    }

    public void renameSubDirToId(IVolume volume, IAttachment attachment, String id)
            throws IOException {
        if (attachment == null)
            throw new NullPointerException("attachment");
        if (volume == null)
            throw new NullPointerException("volume");
//        if (volume == null)
//            throw new IllegalStateException("volume not set");

        File file = attachment.toLocalFile(volume);
        if (!file.exists()) {
            System.out.println(file);
        }

        String subDirName = attachment.getDirName();
        if (Nullables.equals(subDirName, "new") //
                || Nullables.equals(subDirName, "tmp")) {
            IVolumeItem vf = attachment.toVolumeFile(volume);
            vf.moveTo(id, attachment.getFileName());
        }
    }

    @Override
    public boolean beforeRowOperation(JdbcRowOpEvent event)
            throws Exception {
        if (event.getOpType() != JdbcRowOpType.UPDATE)
            return true;

        Object id = idRef.id();
        if (id != null) {
            String idStr = id.toString();

            IDataApplication app = DataApps.fromRequest();
            IVolume volume = app.getVolume(idRef.getClass());

            IAttachmentListing listing = owner.listAttachments();
            for (String category : listing.getAttachmentGroupKeys()) {
                for (IAttachment attachment : listing.getAttachmentGroup(category)) {
//                    renameSubDirToId(volume, attachment, idStr);
                }
            }
        }
        return true;
    }

}
