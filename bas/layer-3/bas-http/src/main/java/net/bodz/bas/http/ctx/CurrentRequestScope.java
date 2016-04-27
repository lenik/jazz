package net.bodz.bas.http.ctx;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.ctx.scope.AbstractScope;
import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.decl.Priority;

@ExcludedFromIndex
public class CurrentRequestScope
        extends AbstractScope {

    @Override
    public IScopeDescriptor tell() {
        IScopeDescriptor current = IScopeDescriptor.DEFAULT;

        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request != null) {
            RequestScopeDescriptor descriptor = new RequestScopeDescriptor(request);
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