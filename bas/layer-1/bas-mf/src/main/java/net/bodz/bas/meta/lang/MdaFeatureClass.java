package net.bodz.bas.meta.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.rtx.IQueryable;

/**
 * MdaFeatureClass can be defined on:
 * 
 * <table>
 * <tr>
 * <th>{@link ElementType}</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>{@link ElementType#TYPE Type}</td>
 * <td>Define the static mdaFeatures for types.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#FIELD Field}</td>
 * <td>Define mdaFeatures preferences for fields.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#METHOD Method}</td>
 * <td>Define mdaFeatures preferences for properties.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#PARAMETER Parameter}</td>
 * <td>Define mdaFeatures preferences for parameters.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#LOCAL_VARIABLE Local Variable}</td>
 * <td>Define mdaFeatures preferences for local variable. (But how to get it?)</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#TYPE_PARAMETER Type Parameter}</td>
 * <td>Define theh static mdaFeatures for type parameter. (Since Java 7)</td>
 * </tr>
 * </table>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MdaFeatureClass {

    Class<? extends IQueryable> value();

}
