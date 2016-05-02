package net.bodz.bas.http.ctx;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.ctx.scope.ScopeIdClass;
import net.bodz.bas.ctx.scope.ScopeTeller;
import net.bodz.bas.ctx.scope._Scope;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@_Scope
@Scope
@ScopeIdClass(HttpServletRequest.class)
@ScopeTeller(RequestScopeTeller.class)
public @interface RequestScope {

}
