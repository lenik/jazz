package net.bodz.lily.entity.manager;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;

public interface IEntityCommandContext
        extends
            IAttributed {

    HttpServletRequest getRequest();

    IVariantMap<String> getParameters();

    DataContext getDataContext();

    JsonResult getResult();

}
