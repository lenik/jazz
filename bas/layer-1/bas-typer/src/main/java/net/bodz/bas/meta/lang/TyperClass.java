package net.bodz.bas.meta.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.rtx.IQueryable;

/**
 * TyperClass can be defined on:
 * 
 * <table>
 * <tr>
 * <th>{@link ElementType}</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>{@link ElementType#TYPE Type}</td>
 * <td>Define the static typers for types.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#FIELD Field}</td>
 * <td>Define typers preferences for fields.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#METHOD Method}</td>
 * <td>Define typers preferences for properties.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#PARAMETER Parameter}</td>
 * <td>Define typers preferences for parameters.</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#LOCAL_VARIABLE Local Variable}</td>
 * <td>Define typers preferences for local variable. (But how to get it?)</td>
 * </tr>
 * <tr>
 * <td>{@link ElementType#TYPE_PARAMETER Type Parameter}</td>
 * <td>Define theh static typers for type parameter. (Since Java 7)</td>
 * </tr>
 * </table>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface TyperClass {

    Class<? extends IQueryable> value();

}
