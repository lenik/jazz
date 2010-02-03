package net.bodz.bas.potato;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.AnnotatedElement;
import java.util.Locale;

public interface IPotatoElement
        extends AnnotatedElement {

    /**
     * @return non-<code>null</code> String.
     */
    String getName();

    /**
     * @param locale
     *            Preferred locale, may be <code>null</code>.
     * @return non-<code>null</code> String.
     */
    String getDisplayName(Locale locale);

    /**
     * @param locale
     *            Preferred locale, may be <code>null</code>.
     * @return <code>null</code> If no description available.
     */
    String getDescription(Locale locale);

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
