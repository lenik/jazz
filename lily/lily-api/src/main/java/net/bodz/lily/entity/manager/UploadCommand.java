package net.bodz.lily.entity.manager;

import java.io.File;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.path.ServiceTargetException;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.DefaultSiteDirs;
import net.bodz.bas.site.file.UploadHandler;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.attachment.IHaveAttachments;

@ForEntityType(IHaveAttachments.class)
public class UploadCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "upload";
    public static final String[] NAMES = { NAME };

    public static final String K_NULL_ID = "incoming";

    public UploadCommand() {
    }

    @Override
    public String getPreferredName() {
        return NAME;
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public boolean isContentCommand() {
        return true;
    }

    @Override
    public UploadProcess createProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new UploadProcess(this, context);
    }

}

class UploadProcess
        extends AbstractEntityCommandProcess<UploadCommand> {

    final String category;

    File startDir;
    IAnchor startAnchor;

    String id;

    public UploadProcess(UploadCommand type, IEntityCommandContext context) {
        super(type, context);
        category = typeInfo.getEntityClass().getName();
    }

    @Override
    public Object execute()
            throws Exception {
        File dataDir = DefaultSiteDirs.getInstance().getDataDir(request);
        IAnchor dataAnchor = DefaultSiteDirs._webApp_;

        startDir = new File(dataDir, category);
        startAnchor = dataAnchor.join(category + "/");

        UploadHandler uploadHandler = new UploadHandler(startDir, startAnchor);
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
