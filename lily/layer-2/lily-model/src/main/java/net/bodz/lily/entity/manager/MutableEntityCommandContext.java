package net.bodz.lily.entity.manager;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.typer.std.MutableAttributes;

public class MutableEntityCommandContext
        extends MutableAttributes
        implements
            IEntityCommandContext {

    HttpServletRequest request;
    IVariantMap<String> parameters;
    DataContext dataContext;
    JsonResult result = new JsonResult();

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public IVariantMap<String> getParameters() {
        return parameters;
    }

    public void setParameters(IVariantMap<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public JsonResult getResult() {
        return result;
    }

    public void setResult(JsonResult result) {
        this.result = result;
    }

}
