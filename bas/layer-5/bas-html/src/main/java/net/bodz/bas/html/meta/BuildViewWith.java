package net.bodz.bas.html.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.http.viz.IHttpViewBuilder;

@Retention(RetentionPolicy.RUNTIME)
public @interface BuildViewWith {

    Class<? extends IHttpViewBuilder<?>>[] value();

}
