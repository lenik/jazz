package net.bodz.bas.data.struct;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface StringBin {

    /**
     * fixed string length in decoded chars.
     */
    int length() default -1;

    /**
     * fixed string size in encoded bytes.
     */
    int size() default -1;

    /**
     * by default, trailing NUL bytes are auto dropped when reading.
     *
     * For raw string, NUL bytes are always included.
     */
    boolean raw() default false;

    /**
     * determined by a length field, the field takes this number of bits.
     */
    int lengthBits() default 0;

    /**
     * determined by a size field, the field takes this number of bits.
     */
    int sizeBits() default 0;

    /**
     * terminator char
     */
    int term() default -1;

    String encoding() default "";

}
