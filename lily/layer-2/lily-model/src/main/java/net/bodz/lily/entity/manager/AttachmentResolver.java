package net.bodz.lily.entity.manager;

import java.io.File;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.repr.content.MutableContent;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostManager;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@ForEntityType(IId.class)
public class AttachmentResolver
        extends AbstractEntityCommand {

    JsonFormOptions jsonFormOptions;

    public AttachmentResolver(IEntityTypeInfo typeInfo) {
        super(typeInfo);
    }

    @Override
    public String getPreferredName() {
        return "attachment";
    }

    @Override
    public boolean isContentCommand() {
        return true;
    }

    @Override
    public Object execute()
            throws Exception {
        IId<?> obj = (IId<?>) resolvedEntity.entity;

        // XXX Redirect to /files/...

        File homeDir = SysProps.userHome;
        File start = new File(homeDir, "sites");

        IVirtualHost vhost = VirtualHostManager.getInstance().resolve(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        String vhostName = vhost.getName();
        File vstart = new File(start, vhostName);

        String entityTypeName = typeInfo.getEntityClass().getSimpleName();
        File tableDir = new File(vstart, entityTypeName);

        String idStr = obj.id().toString();
        File objDir = new File(tableDir, idStr);

        String remainingPath = tokens.getRemainingPath();
        consumedTokenCount = tokens.available();
        File file = new File(objDir, remainingPath);

        if (!file.exists()) {
            logger.warn("Not-Found: " + file);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if (!file.canRead()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not readable.");
            return null;
        }

        URL url = file.toURI().toURL();
        ICacheControl cacheControl = getCacheControl(request, url);
        ResourceTransferer transferer = new ResourceTransferer(request, response);
        transferer.transfer(url, cacheControl);

        return null;
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
            throws LoaderException, ParseException {
        super.readObject(map);

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractEntityCommandBuilder<Builder> {

        public Builder() {
            super(AttachmentResolver.class);
        }

        @Override
        public AttachmentResolver build() {
            return new AttachmentResolver(typeInfo);
        }
    }

}
