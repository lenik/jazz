package net.bodz.lily.entity.manager.cmd;

import java.io.File;
import java.net.URL;

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
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.storage.IVolume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetIncomingFileProcess
        extends AbstractEntityCommandProcess {

    static final Logger logger = LoggerFactory.getLogger(GetIncomingFileProcess.class);

    JsonFormOptions jsonFormOptions;

    public GetIncomingFileProcess(GetIncomingFileCommand type, IEntityCommandContext context) {
        super(type, context);
    }

    @Override
    public Object execute()
            throws Exception {

        IDataApplication dataApp = DataApps.fromRequest();
        IVolume volume = dataApp.getIncomingVolume(typeInfo.getEntityClass());

        String remainingPath = tokens.getRemainingPath();
        consumedTokenCount = tokens.available();

        File file = new File(volume.getLocalDir(), remainingPath);

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
