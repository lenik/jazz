package net.bodz.bas.commons.scripting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface ScriptType {

    @SuppressWarnings("unchecked")
    Class<? extends ScriptClass> value();

}
