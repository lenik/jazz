package net.bodz.lily.entity.manager;

import java.io.File;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.path.ServiceTargetException;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.DefaultSiteDirs;
import net.bodz.bas.site.file.UploadHandler;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.attachment.IHaveAttachmentListing;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@ForEntityType(IHaveAttachmentListing.class)
public class UploadCommand
        extends AbstractEntityCommand {

    final String category;

    File startDir;
    IAnchor startAnchor;

    public static final String K_NULL_ID = "incoming";
    String id;

    public UploadCommand(IEntityTypeInfo typeInfo) {
        super(typeInfo);
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
            Object id = parseId(idString);
            this.id = id.toString();
        } else {
            this.id = K_NULL_ID;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractEntityCommandBuilder<Builder> {

        public Builder() {
            super(UploadCommand.class);
        }

        @Override
        public UploadCommand build() {
            return new UploadCommand(typeInfo);
        }
    }

}
