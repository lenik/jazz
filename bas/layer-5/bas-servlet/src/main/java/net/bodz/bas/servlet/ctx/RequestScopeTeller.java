package net.bodz.bas.servlet.ctx;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.decl.Priority;

@ExcludedFromIndex
public class RequestScopeTeller
        extends AbstractScopeTeller {

    boolean fallbackToDefaultParent = false;
    IScopeInstance defaultParent = IScopeInstance.STATIC;

    @Override
    public IScopeInstance tell() {
        IScopeInstance current = fallbackToDefaultParent ? defaultParent : null;

        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request != null) {
            RequestScopeInstance descriptor = new RequestScopeInstance(request);
            descriptor.setParent(defaultParent);
            current = descriptor;
        }

        return current;
    }

    @Override
    public String tellId() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;
        else
            return request.getRequestURI();
    }

    /**
     * as http request is string-based, don't use it whenever possible.
     */
    @Override
    public int getPriority() {
        return Priority.LOW;
    }

}