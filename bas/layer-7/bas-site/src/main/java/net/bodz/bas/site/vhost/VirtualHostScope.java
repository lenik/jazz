package net.bodz.bas.site.vhost;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import net.bodz.bas.ctx.scope.ScopeIdClass;
import net.bodz.bas.ctx.scope.ScopeTeller;
import net.bodz.bas.ctx.scope._Scope;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@_Scope
@Scope
@ScopeIdClass(IVirtualHost.class)
@ScopeTeller(VirtualHostScopeTeller.class)
public @interface VirtualHostScope {

}
