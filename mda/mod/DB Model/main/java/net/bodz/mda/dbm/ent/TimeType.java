package net.bodz.mda.dbm.ent;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD })
public @interface TimeType {

    TimeParts value() default TimeParts.DATETIME;

    /**
     * @see SimpleDateFormat
     */
    String format() default "";

    boolean local() default false;

}
