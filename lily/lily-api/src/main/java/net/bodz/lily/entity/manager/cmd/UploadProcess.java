package net.bodz.lily.entity.manager.cmd;

import java.io.File;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.path.ServiceTargetException;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.file.UploadHandler;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.storage.IVolume;

public class UploadProcess
        extends AbstractEntityCommandProcess {

    String id;

    public UploadProcess(UploadCommand type, IEntityCommandContext context) {
        super(type, context);
    }

    @Override
    public Object execute()
            throws Exception {
        File localDir;
        IAnchor anchor;

        if (id == null) {
            IVolume volume = dataApp.getIncomingVolume(typeInfo.getEntityClass());
            localDir = volume.getLocalDir();
            anchor = volume.getVolumeAnchor();
        } else {
            IVolume volume = dataApp.getEntityVolume(typeInfo.getEntityClass());
            localDir = volume.getLocalDir();
            anchor = volume.getVolumeAnchor();
            localDir = new File(localDir, id);
            anchor = anchor.join(id).enter();
        }

        UploadHandler uploadHandler = new UploadHandler(localDir, anchor);
        try {
            return uploadHandler.handlePostRequest(request);
        } catch (Exception e) {
            throw new ServiceTargetException("upload handler: " + e.getMessage(), e);
        }
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
        String idString = map.getString("id");
        if (idString != null) {
            Object id = typeInfo.parseSimpleId(idString);
            this.id = id.toString();
        } else {
            this.id = UploadCommand.K_NULL_ID;
        }
    }

}
