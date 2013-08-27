package net.bodz.bas.meta.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.rtx.IQueryable;

/**
 * TypeFeatureClass can be defined on:
 * 
 * <table>
 * <tr>
 * <th>{@link ElementType}</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>{@link ElementType#TYPE Type}</td>
 * <td>Define the static typeFeatures for types.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#FIELD Field}</td>
 * <td>Define typeFeatures preferences for fields.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#METHOD Method}</td>
 * <td>Define typeFeatures preferences for properties.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#PARAMETER Parameter}</td>
 * <td>Define typeFeatures preferences for parameters.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#LOCAL_VARIABLE Local Variable}</td>
 * <td>Define typeFeatures preferences for local variable. (But how to get it?)</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#TYPE_PARAMETER Type Parameter}</td>
 * <td>Define theh static typeFeatures for type parameter. (Since Java 7)</td>
 * </tr>
 * </table>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeFeatureClass {

    Class<? extends IQueryable> value();

}
