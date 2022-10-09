package net.bodz.lily.entity.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.repr.path.ITokenProcessor;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;

public interface IEntityCommandContext
        extends
            IAttributed,
            ITokenProcessor {

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    IVariantMap<String> getParameters();

    DataContext getDataContext();

    EntityInfo getEntityInfo();

    JsonResult getResult();

}
