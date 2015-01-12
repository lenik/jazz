package net.bodz.bas.http.ctx;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.ctx.inject.AbstractScope;
import net.bodz.bas.ctx.scope.IScopeToken;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.meta.decl.Priority;

@ExcludedFromIndex
public class CurrentRequestScope
        extends AbstractScope {

    private Map<String, String[]> fallbackParameterMap;

    public CurrentRequestScope() {
        fallbackParameterMap = new HashMap<String, String[]>();
    }

    @Override
    public IScopeToken tell() {
        IScopeToken parent = IScopeToken.DEFAULT;

        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request != null) {
            RequestScopeToken token = new RequestScopeToken(request);
            token.setParent(parent);
            parent = token;
        }

        return parent;
    }

    protected Map<String, String[]> getParameterMap() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request != null)
            return request.getParameterMap();
        else
            return fallbackParameterMap;
    }

    @Override
    public boolean contains(String name) {
        return getParameterMap().containsKey(name);
    }

    @Override
    public Object resolve(String name) {
        return getParameterMap().get(name);
    }

    /**
     * as http request is string-based, don't use it whenever possible.
     */
    @Override
    public int getPriority() {
        return Priority.LOW;
    }

}