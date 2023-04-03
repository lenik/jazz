package net.bodz.bas.data.struct;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ListBin {

    /**
     * fixed list length in count of items.
     */
    int nitem() default -1;

    /**
     * fixed list length in count of bytes.
     */
    int size() default -1;

    /**
     * determined by a length field, the field takes this number of bits.
     */
    int nitemBits() default 0;

    /**
     * determined by a size field, the field takes this number of bits.
     */
    int sizeBits() default 0;

    /**
     * terminator byte
     */
    int term() default -1;

}
