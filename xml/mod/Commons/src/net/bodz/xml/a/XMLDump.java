package net.bodz.xml.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.FIELD,
        java.lang.annotation.ElementType.METHOD })
public @interface XMLDump {

    /**
     * Specify path to dump at.
     * 
     * <code>
     * <ul>
     * <li>PATH: PREFIX/@ATTRIBUTE
     * <li>PATH: PREFIX/ELEMENT
     * <li>PREFIX: 
     * <li>PREFIX: PREFIX ELEMENT/
     * <li>ELEMENT: TAGNAME
     * <li>ELEMENT: TAGNAME '*'
     * </ul>
     * </code>
     * 
     * <code>TAGNAME*</code> means TAGNAME under its parent may have multiple occurences.
     */
    String value() default "";

    /**
     * Default using {@link #toString()} method.
     */
    String format() default "";

    int TEXT    = 0;
    int CDATA   = 1;
    int PCDATA  = 2;
    int COMMENT = 3;

    /**
     * @see #TEXT
     * @see #CDATA
     * @see #PCDATA
     * @see #COMMENT
     */
    int data() default TEXT;

}