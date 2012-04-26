package net.bodz.bas.ui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.model.IFactory;
import net.bodz.bas.ui.util.VoidFactory_String;

@Retention(RetentionPolicy.RUNTIME)
public @interface Label {

    /**
     * label text
     */
    String value() default "";

    /**
     * create(usage) -> label text
     */
    Class<? extends IFactory<String>> factory() default VoidFactory_String.class;

}
