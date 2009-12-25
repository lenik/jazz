package net.bodz.bas.typeinfo.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.typeinfo.TypeInfo;

/**
 * It's recommended to use static class method <code>getTypeInfo(): {@link TypeInfo}</code>, rather then {@link TypeInfoClass} annotation to
 * implement {@link TypeInfo} singletons.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
// , ElementType.TYPE_PARAMETER for Java-7
public @interface TypeInfoClass {

    Class<? extends TypeInfo> value();

}
