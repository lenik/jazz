package net.bodz.bas.repr.html;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HtmlViewBuilder {

    Class<? extends IHtmlViewBuilder<?>>[] value();

}
