package net.bodz.bas.ui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.lang.arch.IFactory;
import net.bodz.bas.ui.util.VoidFactory_String;

@Retention(RetentionPolicy.RUNTIME)
public @interface Icon {

    /**
     * resource-path
     */
    String[] value() default {};

    /**
     * create(usage) -> resource-path or ImageData, where usage is `big', `small', etc.
     */
    Class<? extends IFactory<?>> factory() default VoidFactory_String.class;

}
