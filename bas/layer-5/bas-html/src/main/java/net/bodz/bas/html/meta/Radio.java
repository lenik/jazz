package net.bodz.bas.html.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Named;
import javax.inject.Qualifier;

@Named("radio")
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Radio {

}
