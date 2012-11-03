package net.bodz.bas.gui.mda;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.gui.util.VoidFactory_String;
import net.bodz.bas.model.IFactory;

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
