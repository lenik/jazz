package net.bodz.bas.type.traits;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * It's recommended to use static class method <code>getTypeInfo(): {@link ITypeInfo}</code>, rather then {@link UseTypeInfo} annotation to
 * implement {@link ITypeInfo} singletons.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
// , ElementType.TYPE_PARAMETER for Java-7
public @interface UseTypeInfo {

    Class<? extends ITypeInfo> value();

}
