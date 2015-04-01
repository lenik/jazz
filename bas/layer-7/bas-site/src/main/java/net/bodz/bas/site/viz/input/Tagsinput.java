package net.bodz.bas.site.viz.input;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, //
        ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
public @interface Tagsinput {

    Class<? extends ITagTyper<?>> typer();

}
