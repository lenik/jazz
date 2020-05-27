package net.bodz.bas.servlet.ctx;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.servlet.http.HttpSession;

import net.bodz.bas.ctx.ScopeType;
import net.bodz.bas.ctx.scope.ScopeIdClass;
import net.bodz.bas.ctx.scope.ScopeTeller;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@ScopeType
@Scope
@ScopeIdClass(HttpSession.class)
@ScopeTeller(SessionScopeTeller.class)
public @interface SessionScope {

}
