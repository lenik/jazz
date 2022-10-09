package net.bodz.lily.entity.manager;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.repr.path.DefaultTokenProcessor;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.typer.std.MutableAttributes;

public class MutableEntityCommandContext
        extends DefaultTokenProcessor
        implements
            IEntityCommandContext {

    private static final long serialVersionUID = 1L;

    IAttributed attributes = new MutableAttributes();

    IVariantMap<String> parameters;

    HttpServletRequest request;
    DataContext dataContext;

    EntityInfo entityInfo;

    JsonResult result = new JsonResult();

    public MutableEntityCommandContext(IPathArrival previous, ITokenQueue tokenQueue, IVariantMap<String> q) {
        super(previous, tokenQueue);
        this.parameters = q;
        // this.request = CurrentHttpService.getRequestOpt();
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        return attributes.getAttribute(name, defaultValue);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.setAttribute(name, value);
    }

    @Override
    public IVariantMap<String> getParameters() {
        return parameters;
    }

    public void setParameters(IVariantMap<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    @Override
    public JsonResult getResult() {
        return result;
    }

    public void setResult(JsonResult result) {
        this.result = result;
    }

}
