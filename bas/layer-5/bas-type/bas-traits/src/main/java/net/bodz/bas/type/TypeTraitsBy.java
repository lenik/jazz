package net.bodz.bas.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * It's recommended to use static class method <code>getTypeInfo(): {@link ITypeTraits}</code>, rather then {@link TypeTraitsBy} annotation to
 * implement {@link ITypeTraits} singletons.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
// , ElementType.TYPE_PARAMETER for Java-7
public @interface TypeTraitsBy {

    Class<? extends ITypeTraits<?>> value();

}
