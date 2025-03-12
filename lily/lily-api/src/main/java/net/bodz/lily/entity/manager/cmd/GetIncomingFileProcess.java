package net.bodz.lily.entity.manager.cmd;

import java.nio.file.Path;

import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.html.servlet.NoRender;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.storage.IVolume;

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

        Path file = volume.getLocalDir().resolve(remainingPath);

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
                .maxAge(maxAge)//
                .transfer();

        // return file;
        return NoRender.INSTANCE;
    }

    /**
     * 1 day by default.
     */
    int maxAge = 86400;

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

}
