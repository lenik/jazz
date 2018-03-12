package net.bodz.bas.ctx.scope;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.ctx.ScopeType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ScopeTeller {

    Class<? extends IScopeTeller> value();

    class fn {

        /**
         * @return Non-<code>null</code> value.
         */
        public static IScopeTeller fromScopedObject(Class<?> objectType) {
            Annotation aFooScope = ScopeType.fn.getScopeAnnotation(objectType);
            if (aFooScope == null) {
                // prototype by default
                return LocalScopeTeller.INSTANCE;
            } else {
                return fromScopeAnnotationType(aFooScope.annotationType());
            }
        }

        public static IScopeTeller fromScopeAnnotationType(Class<? extends Annotation> annotationClass) {
            ScopeTeller aScopeTeller = annotationClass.getAnnotation(ScopeTeller.class);
            return resolve(aScopeTeller);
        }

        public static IScopeTeller resolve(ScopeTeller aScopeTeller) {
            Class<? extends IScopeTeller> scopeTellerClass = aScopeTeller == null ? null : aScopeTeller.value();
            if (scopeTellerClass != null) {
                return SingletonUtil.instantiateCached(scopeTellerClass);
            } else
                return IScopeTeller.STATIC;
        }

    }

}
