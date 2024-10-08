package net.bodz.lily.entity.manager.cmd;

import java.io.File;
import java.net.URL;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.html.servlet.NoRender;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.content.MutableContent;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.attachment.IAttachment;
import net.bodz.lily.entity.attachment.IAttachmentListing;
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
        File objDir = volume.getLocalFile(idPath);

        String remainingPath = tokens.getRemainingPath();
        consumedTokenCount = tokens.available();

        if (obj instanceof IHaveAttachments) {
            if (StringPred.isDecimal(remainingPath) //
                    && remainingPath.length() < 10) {
                int attIndex = Integer.parseInt(remainingPath);

                IHaveAttachments holder = (IHaveAttachments) obj;
                IAttachmentListing attachments = holder.listAttachments();
                IAttachment attachment = attachments.getAttachment(attIndex);
                if (attachment != null) {
                    remainingPath = attachment.getSHA1FileName();
                }
            }
        }

        File file = new File(objDir, remainingPath);

        if (! file.exists()) {
            logger.warn("Not-Found: " + file);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if (! file.canRead()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
            return null;
        }

        URL url = file.toURI().toURL();
        ICacheControl cacheControl = getCacheControl(request, url);
        ResourceTransferer transferer = new ResourceTransferer(request, response);
        transferer.transfer(url, cacheControl);

        // return file;
        return NoRender.INSTANCE;
    }

    /**
     * 1 day by default.
     */
    private int maxAge = 86400;

    public ICacheControl getCacheControl(HttpServletRequest req, URL url) {
        MutableContent content = new MutableContent();
        content.setMaxAge(maxAge);
        return content;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

}
