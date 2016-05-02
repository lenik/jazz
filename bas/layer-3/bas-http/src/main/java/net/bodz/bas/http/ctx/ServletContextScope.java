package net.bodz.bas.http.ctx;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.servlet.ServletContext;

import net.bodz.bas.ctx.scope.ScopeIdClass;
import net.bodz.bas.ctx.scope.ScopeTeller;
import net.bodz.bas.ctx.scope._Scope;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@_Scope
@Scope
@ScopeIdClass(ServletContext.class)
@ScopeTeller(ServletContextScopeTeller.class)
public @interface ServletContextScope {

}
