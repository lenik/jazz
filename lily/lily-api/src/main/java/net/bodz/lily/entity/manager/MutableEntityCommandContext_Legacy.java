package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.repr.path.DefaultTokenProcessor;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.typer.std.MutableAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MutableEntityCommandContext_Legacy
        extends DefaultTokenProcessor
        implements
            IEntityCommandContext_Legacy {

    private static final long serialVersionUID = 1L;

    IAttributed attributes = new MutableAttributes();

    IVariantMap<String> parameters;

    HttpServletRequest request;
    HttpServletResponse response;

    DataContext dataContext;

    ResolvedEntity entityInfo;

    JsonResult result = new JsonResult();

    public MutableEntityCommandContext_Legacy(IPathArrival previous, ITokenQueue tokenQueue, IVariantMap<String> q) {
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

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public ResolvedEntity getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(ResolvedEntity entityInfo) {
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
