package net.bodz.lily.entity.manager.cmd;

import java.nio.file.Path;

import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.html.servlet.NoRender;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.attachment.IAttachment;
import net.bodz.lily.entity.attachment.IAttachmentManifest;
import net.bodz.lily.entity.attachment.IHaveAttachments;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.ResolvedEntity;
import net.bodz.lily.storage.IVolume;

public class AttachmentResolveProcess
        extends AbstractEntityCommandProcess {

    static final Logger logger = LoggerFactory.getLogger(AttachmentResolveProcess.class);

    JsonFormOptions jsonFormOptions;
    ResolvedEntity resolvedEntity;

    public AttachmentResolveProcess(AttachmentResolveCommand type, IEntityCommandContext context,
            ResolvedEntity resolvedEntity) {
        super(type, context);
        this.resolvedEntity = resolvedEntity;
    }

    @Override
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

    @Override
    public Object execute()
            throws Exception {
        IId<?> obj = (IId<?>) resolvedEntity.entity;

//        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
//        if (vhost == null)
//            throw new IllegalUsageException("No corresponding vhost.");

        IDataApplication dataApp = DataApps.fromRequest();
        IVolume volume = dataApp.getEntityVolume(typeInfo.getEntityClass());

        String idPath = obj.getIdPath();
        Path objDir = volume.getLocalFile(idPath);

        String remainingPath = tokens.getRemainingPath();
        consumedTokenCount = tokens.available();

        if (obj instanceof IHaveAttachments) {
            if (StringPred.isDecimal(remainingPath) //
                    && remainingPath.length() < 10) {
                int attIndex = Integer.parseInt(remainingPath);

                IHaveAttachments holder = (IHaveAttachments) obj;
                IAttachmentManifest attachments = holder.attachmentManifest();
                IAttachment attachment = attachments.get(attIndex);
                if (attachment != null) {
                    remainingPath = attachment.getSHA1FileName();
                }
            }
        }

        Path file = objDir.resolve(remainingPath);

        if (FileFn.notExists(file)) {
            logger.warn("Not-Found: " + file);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if (!FileFn.canRead(file)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
            return null;
        }

        new ResourceTransferer()//
                .request(request, response)//
                .path(file)//
                .transfer();

        // return file;
        return NoRender.INSTANCE;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

}
