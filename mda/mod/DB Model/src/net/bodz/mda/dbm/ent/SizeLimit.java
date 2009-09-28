package net.bodz.mda.dbm.ent;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.ResultSetMetaData;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target( { ElementType.FIELD, ElementType.METHOD })
public @interface SizeLimit {

    /**
     * Specify the precision of numbers, or max-length of strings.
     * 
     * <p>
     * Optimiazations: the underlying type will be auto determined if the
     * precision value is specified.
     * <ul>
     * <li>For integers: the types may be BYTE, TINYINT, SMALLINT, BIGINT,
     * LARGEINT, etc.
     * <li>For floats, the types may be FLOAT, DOUBLE, DECIMAL, NUMERIC, etc.
     * </ul>
     * <li>For strings, the types may be VARCHAR, LONGVARCHAR, and negative
     * values will get the fixed-size versions (such as CHAR, NCHAR, etc.)
     * <li>For binary, the types may be BINARY, BLOB, etc. </ul>
     * 
     * @see ResultSetMetaData#getPrecision(int)
     */
    int value() default 0;

    /**
     * Specify the scale of decimals.
     * 
     * @see ResultSetMetaData#getScale(int)
     */
    int scale() default 0;

}
