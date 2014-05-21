package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.context.IContext;
import net.bodz.bas.context.IContextTeller;

public class CurrentRequestContextTeller
        implements IContextTeller {

    @Override
    public IContext tell() {
        IContext context = IContext.DEFAULT;

        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request != null) {
            RequestContext requestContext = new RequestContext(request);
            requestContext.setParent(context);
            context = requestContext;
        }

        return context;
    }

}