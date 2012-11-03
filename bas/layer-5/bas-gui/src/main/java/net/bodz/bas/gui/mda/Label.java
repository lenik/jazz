package net.bodz.bas.gui.mda;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.gui.util.VoidFactory_String;
import net.bodz.bas.model.IFactory;

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
