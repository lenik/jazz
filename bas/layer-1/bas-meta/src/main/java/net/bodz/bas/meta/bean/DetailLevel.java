package net.bodz.bas.meta.bean;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.meta.meta.Implicit;

/**
 * The detail level controls whether this element is displayed. When user specifies a display detail
 * level, then only elements with detail level smaller then the the display detail level is
 * displayed.
 * <p>
 * Detail level compared to Java Beans' {@link com.googlecode.openbeans.PropertyDescriptor}:
 * <table>
 * <tr>
 * <th>Java Bean</th>
 * <th>Detail Level</th>
 * </tr>
 * <tr>
 * <td> {@link com.googlecode.openbeans.FeatureDescriptor#isPreferred()}</td>
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
 * <td> {@link com.googlecode.openbeans.FeatureDescriptor#isExpert()}</td>
 * <td> {@value #DETAIL_EXPERT}</td>
 * </tr>
 * <tr>
 * <td> {@link com.googlecode.openbeans.FeatureDescriptor#isHidden()}</td>
 * <td> {@value #DETAIL_HIDDEN}</td>
 * </tr>
 * </table>
 */
@Implicit
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DetailLevel {

    int CRITICAL = -1; // "preferred"
    int NORMAL = 0; // public
    int NORMAL2 = 1; // public
    int NORMAL3 = 2; // public
    int DETAIL = 10; // protected
    int DETAIL2 = 11; // protected
    int DETAIL3 = 12; // protected
    int EXPERT = 30; // protected
    int EXPERT2 = 31; // package-protected
    int EXPERT3 = 32; // package-protected
    int HIDDEN = 40; // private

    int value();

}
