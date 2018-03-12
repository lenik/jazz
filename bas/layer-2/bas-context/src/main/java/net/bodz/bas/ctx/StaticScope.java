package net.bodz.bas.ctx;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import net.bodz.bas.ctx.scope.ScopeTeller;
import net.bodz.bas.ctx.scope.StaticScopeTeller;


@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@ScopeType
@Scope
@ScopeTeller(StaticScopeTeller.class)
public @interface StaticScope {

}
