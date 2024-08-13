package net.bodz.lily.entity.manager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.repr.path.ITokenProcessor;
import net.bodz.bas.rtx.IMutableAttributes;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;

public interface IEntityCommandContext_Legacy
        extends
            IMutableAttributes,
            ITokenProcessor {

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    IVariantMap<String> getParameters();

    DataContext getDataContext();

    ResolvedEntity getEntityInfo();

    JsonResult getResult();

}
