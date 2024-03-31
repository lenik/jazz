package net.bodz.lily.entity.attachment;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

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
import net.bodz.lily.entity.manager.RowOpListeners;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeItem;

/**
 * See {@link RowOpListeners} which is indexed and annotated class like {@link IHaveAttachments}
 * lists this class as the row-op listener.
 *
 * @see RowOpListeners
 */
public class UpdateAttachments
        implements
            IJdbcRowOpListener {

    static final Logger logger = LoggerFactory.getLogger(UpdateAttachments.class);

    IId<?> context;

    public <T extends IId<?>> UpdateAttachments(T obj) {
        this.context = obj;
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
        if (! file.exists()) {
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
        if (! (context instanceof IHaveAttachments))
            return true;
        IHaveAttachments owner = (IHaveAttachments) context;

        if (event.getOpType() != JdbcRowOpType.UPDATE)
            return true;

        Object id = context.id();
        if (id != null) {
            String idStr = id.toString();
//
            IDataApplication app = DataApps.fromRequest();
            IVolume volume = app.getEntityVolume(context.getClass());

            IAttachmentListing listing = owner.listAttachments();
            for (String category : listing.getAttachmentGroupKeys()) {
                Collection<IAttachment> group = listing.getAttachmentGroup(category);
                if (! group.isEmpty())
                    for (IAttachment attachment : group) {
                        renameSubDirToId(volume, attachment, idStr);
                    }
            }
        }
        return true;
    }

}
