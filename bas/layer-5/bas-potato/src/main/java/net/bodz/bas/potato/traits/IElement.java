package net.bodz.bas.potato.traits;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.i18n.dom.DomainString;

public interface IElement
        extends AnnotatedElement {

    /**
     * The type where this element is declared.
     * 
     * @return The declaring type.
     */
    Class<?> getDeclaringType();

    /**
     * The unique name of the potato element.
     * 
     * @return non-<code>null</code> name.
     */
    String getName();

    /**
     * The human readable display name.
     * 
     * @return non-<code>null</code> string of display name.
     */
    DomainString getDisplayName();

    /**
     * @return <code>null</code> If no description available.
     */
    DomainString getDescription();

    /**
     * The preference level controls whether this element is displayed. When user specifies a
     * display preference, then only elements with preference level smaller then the the display
     * preference is displayed. For negative preference levels, they are hidden by default and only
     * shown if show-hidden is enabled.
     * <p>
     * Preference level compared to Java Beans' {@link PropertyDescriptor}:
     * <table>
     * <tr>
     * <th>Java Bean</th>
     * <th>Preference Level</th>
     * </tr>
     * <tr>
     * <td> {@link FeatureDescriptor#isHidden()}</td>
     * <td>-1</td>
     * </tr>
     * <tr>
     * <td> {@link FeatureDescriptor#isPreferred()}</td>
     * <td>0</td>
     * </tr>
     * <tr>
     * <td><i>(Default)</i></td>
     * <td>1</td>
     * </tr>
     * <tr>
     * <td> {@link FeatureDescriptor#isExpert()}</td>
     * <td>2</td>
     * </tr>
     * </table>
     */
    int getPreferenceLevel();

    /**
     * Specify the category names this element belongs to.
     * 
     * @return Empty string array if no tag is specified.
     */
    String[] getTags();

}
