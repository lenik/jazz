package net.bodz.lily.entity.manager.cmd;

import java.io.File;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.path.ServiceTargetException;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.file.UploadHandler;
import net.bodz.bas.site.file.UploadResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.storage.IVolume;

public class ImportProcess
        extends AbstractEntityCommandProcess {

    String encoding;
    String delim;

    // String id;

    public ImportProcess(ImportCommand type, IEntityCommandContext context) {
        super(type, context);
    }

    @Override
    public Object execute()
            throws Exception {
        IVolume volume = dataApp.getIncomingVolume(typeInfo.getEntityClass());
        File localDir = volume.getLocalDir();
        IAnchor anchor = volume.getVolumeAnchor();
        UploadHandler uploadHandler = new UploadHandler(localDir, anchor);
        UploadResult result;
        try {
            result = uploadHandler.handlePostRequest(request);
        } catch (Exception e) {
            throw new ServiceTargetException("upload handler: " + e.getMessage(), e);
        }

        return result;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        encoding = map.getString("encoding", encoding);
        delim = map.getString("delim", delim);
    }

}
