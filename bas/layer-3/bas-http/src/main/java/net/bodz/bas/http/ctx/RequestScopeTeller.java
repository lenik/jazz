package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.ctx.scope.AbstractScopeTeller;
import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.decl.Priority;

@ExcludedFromIndex
public class RequestScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        IScopeInstance current = IScopeInstance.STATIC;

        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request != null) {
            RequestScopeInstance descriptor = new RequestScopeInstance(request);
            descriptor.setParent(current);
            current = descriptor;
        }

        return current;
    }

    /**
     * as http request is string-based, don't use it whenever possible.
     */
    @Override
    public int getPriority() {
        return Priority.LOW;
    }

}