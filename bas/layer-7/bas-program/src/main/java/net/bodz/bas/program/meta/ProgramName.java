package net.bodz.bas.program.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.c.annotation.AnnotationParseUtil;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProgramName {

    String value();

    class fn
            extends AnnotationParseUtil {

        public static String getValue(Class<?> type) {
            return getValue(type, null);
        }

        public static String getValue(Class<?> type, Boolean toUpperCase) {
            ProgramName pn = type.getAnnotation(ProgramName.class);
            if (pn != null)
                return pn.value();
            String name = type.getSimpleName();
            if (toUpperCase != null)
                name = toUpperCase ? name.toUpperCase() : name.toLowerCase();
            return name;
        }

    }

}
