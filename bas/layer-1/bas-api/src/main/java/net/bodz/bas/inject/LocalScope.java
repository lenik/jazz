package net.bodz.bas.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface LocalScope {

}
