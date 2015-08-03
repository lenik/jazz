package net.bodz.bas.http.viz;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.rtx.AbstractQueryable;
import net.bodz.bas.rtx.IQueryable;

public abstract class AbstractHttpViewContext
        extends AbstractQueryable
        implements IHttpViewContext {

    private IQueryable queryContext;

    public IQueryable getQueryContext() {
        return queryContext;
    }

    public void setQueryContext(IQueryable queryContext) {
        this.queryContext = queryContext;
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationClass) {
        HttpServletRequest request = getRequest();

        Object requestAttribute = request.getAttribute(specificationClass.getName());
        if (requestAttribute != null)
            return specificationClass.cast(requestAttribute);

        if (queryContext != null) {
            spec_t impl = queryContext.query(specificationClass);
            if (impl != null)
                return impl;
        }

        return super.query(specificationClass);
    }

    @Override
    public Object query(String specificationId) {
        HttpServletRequest request = getRequest();

        Object requestAttribute = request.getAttribute(specificationId);
        if (requestAttribute != null)
            return requestAttribute;

        if (queryContext != null) {
            Object impl = queryContext.query(specificationId);
            if (impl != null)
                return impl;
        }

        return super.query(specificationId);
    }

    @Override
    public IHttpViewContext getRoot() {
        IHttpViewContext node = this;
        IHttpViewContext parent;
        while ((parent = node.getParent()) != null)
            node = parent;
        return node;
    }

}
