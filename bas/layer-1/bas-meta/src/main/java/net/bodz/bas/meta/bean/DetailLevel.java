package net.bodz.bas.meta.bean;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The detail level controls whether this element is displayed. When user specifies a display detail
 * level, then only elements with detail level smaller then the the display detail level is
 * displayed.
 * <p>
 * Detail level compared to Java Beans' {@link java.beans.PropertyDescriptor}:
 * <table>
 * <tr>
 * <th>Java Bean</th>
 * <th>Detail Level</th>
 * </tr>
 * <tr>
 * <td> {@link java.beans.FeatureDescriptor#isPreferred()}</td>
 * <td> {@value #DETAIL_CRITICAL}</td>
 * </tr>
 * <tr>
 * <td><i>(Default)</i></td>
 * <td> {@value #DETAIL_NORMAL}</td>
 * </tr>
 * <tr>
 * <td>include children...</i></td>
 * <td> {@value #DETAIL_EXTEND}</td>
 * </tr>
 * <tr>
 * <td> {@link java.beans.FeatureDescriptor#isExpert()}</td>
 * <td> {@value #DETAIL_EXPERT}</td>
 * </tr>
 * <tr>
 * <td> {@link java.beans.FeatureDescriptor#isHidden()}</td>
 * <td> {@value #DETAIL_HIDDEN}</td>
 * </tr>
 * </table>
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DetailLevel {

    int CRITICAL = -1; // "preferred"
    int NORMAL = 0; // public
    int DETAIL = 1; // protected
    int EXTEND = 2; // child map, etc.
    int EXPERT = 3; // protected
    int EXPERT2 = 4; // package-protected
    int HIDDEN = 10; // private

    int value();

}
