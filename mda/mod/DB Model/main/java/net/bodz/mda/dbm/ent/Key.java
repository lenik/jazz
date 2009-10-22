package net.bodz.mda.dbm.ent;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Foreign Key Reference
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD })
public @interface Key {

    String group() default "";

    /**
     * Target class: which class this foreign key references to.
     */
    Class<?> value() default void.class;

    /**
     * Target (class-)name.
     * <p>
     * If target name isn't specified, then target class specified by
     * <code>value()</code> is used. If both are specified, then target is used
     * as a field-selector. If neither is specified, then this must be an
     * addition field of the group.
     * 
     * <p>
     * Example:
     * 
     * <pre>
     * class Address {
     * 
     *     &#064;Key(Country.class)
     *     int country;
     * 
     *     &#064;Key(value = Province.class, target = &quot;cityCode&quot;)
     *     int city;
     *     &#064;Key(value = Province.class, target = &quot;provinceCode&quot;)
     *     int province;
     * 
     *     &#064;Key(target = &quot;Province.cityCode&quot;)
     *     int city;
     *     &#064;Key(target = &quot;Province.provinceCode&quot;)
     *     int province;
     * 
     * }
     * </pre>
     */
    String target() default "";

    /**
     * Approx dimension.
     * <ul>
     * <li><code>0</code> is undefined.
     * <li><code>1</code> means one-to-one.
     * <li><code>-1</code> means many (many-to-one.
     * </ul>
     * 
     * Example:
     * 
     * <pre>
     * class Student {
     * 
     *     // A school may contain 1000 students in average. 
     *     &#064;Key(value = School.class, dim = 1000)
     *     int    schoolId; // which school this student belongs to.
     * 
     *     &#064;Key(value = User.class, dim = 1)
     *     String loginName; // one and only one user a student corresponds to.
     * 
     * }
     * </pre>
     */
    int dim() default -1;

    /**
     * The verb describes the behavior of the relation defined by this key-ref.
     * <p>
     * Example:
     * 
     * <pre>
     * class Employee {
     * 
     *     &#064;Key(value = Company.class, verb = &quot;Work For&quot;)
     *     int companyId;
     * 
     * }
     * </pre>
     */
    String verb() default "";

}
