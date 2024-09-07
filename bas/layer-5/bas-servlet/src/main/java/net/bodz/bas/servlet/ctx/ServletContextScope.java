package net.bodz.bas.servlet.ctx;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import net.bodz.bas.ctx.ScopeType;
import net.bodz.bas.ctx.scope.ScopeIdClass;
import net.bodz.bas.ctx.scope.ScopeTeller;

import jakarta.servlet.ServletContext;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@ScopeType
@Scope
@ScopeIdClass(ServletContext.class)
@ScopeTeller(ServletContextScopeTeller.class)
public @interface ServletContextScope {

}
