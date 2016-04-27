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
    public Object query(String... args) {
        HttpServletRequest request = getRequest();

        if (args.length == 1) {
            String name = args[0];
            Object requestAttribute = request.getAttribute(name);
            if (requestAttribute != null)
                return requestAttribute;
        }

        if (queryContext != null) {
            Object impl = queryContext.query(args);
            if (impl != null)
                return impl;
        }

        return super.query(args);
    }

}
