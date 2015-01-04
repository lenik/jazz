package net.bodz.bas.html.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.html.viz.IHtmlViewBuilder;

@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlViewBuilder {

    Class<? extends IHtmlViewBuilder<?>>[] value();

}
