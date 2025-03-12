package net.bodz.lily.entity.attachment;

import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.manager.IJdbcRowOpListener;
import net.bodz.lily.entity.manager.JdbcRowOpEvent;
import net.bodz.lily.entity.manager.JdbcRowOpType;
import net.bodz.lily.entity.manager.RowOpAware;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeItem;

/**
 * See {@link RowOpAware} which is indexed and annotated class like {@link IHaveAttachments}
 * lists this class as the row-op listener.
 *
 * @see RowOpAware
 */
public class UpdateAttachments
        implements IJdbcRowOpListener {

    static final Logger logger = LoggerFactory.getLogger(UpdateAttachments.class);

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

        Path file = attachment.toPath(volume);
        if (FileFn.notExists(file)) {
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
    public boolean beforeRowOperation(JdbcRowOpEvent event, Object context)
            throws Exception {
        if (!(context instanceof IHaveAttachments))
            return true;
        IHaveAttachments owner = (IHaveAttachments) context;

        if (event.getOpType() != JdbcRowOpType.UPDATE)
            return true;

        Object id;
        if (context instanceof IId<?> iid)
            id = iid.id();
        else
            return true;

        if (id != null) {
            String idStr = id.toString();

            IDataApplication app = DataApps.fromRequest();
            IVolume volume = app.getEntityVolume(context.getClass());

            IAttachmentManifest listing = owner.attachmentManifest();
            for (IAttachment attachment : listing.getData()) {
                renameSubDirToId(volume, attachment, idStr);
            }
        }
        return true;
    }

}
