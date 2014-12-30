package net.bodz.bas.html.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.html.viz.IHtmlViewBuilder;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HtmlViewBuilder {

    Class<? extends IHtmlViewBuilder<?>>[] value();

}
