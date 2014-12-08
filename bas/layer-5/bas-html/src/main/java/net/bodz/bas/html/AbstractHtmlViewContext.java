package net.bodz.bas.html;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.rtx.AbstractQueryable;

public abstract class AbstractHtmlViewContext
        extends AbstractQueryable
        implements IHtmlViewContext {

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationClass) {
        HttpServletRequest request = getRequest();

        Object requestAttribute = request.getAttribute(specificationClass.getName());
        if (requestAttribute != null)
            return specificationClass.cast(requestAttribute);

        return super.query(specificationClass);
    }

    @Override
    public Object query(String specificationId) {
        HttpServletRequest request = getRequest();

        Object requestAttribute = request.getAttribute(specificationId);
        if (requestAttribute != null)
            return requestAttribute;

        return super.query(specificationId);
    }

    @Override
    public IHtmlViewContext getRoot() {
        IHtmlViewContext node = this;
        IHtmlViewContext parent;
        while ((parent = node.getParent()) != null)
            node = parent;
        return node;
    }

}
