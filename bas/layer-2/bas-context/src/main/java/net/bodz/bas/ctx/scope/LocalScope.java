package net.bodz.bas.ctx.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Aka. prototype scope.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@_Scope
@Scope
@ScopeTeller(LocalScopeTeller.class)
public @interface LocalScope {

}
