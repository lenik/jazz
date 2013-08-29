package net.bodz.bas.i18n.dom1;

import java.lang.reflect.Modifier;

import net.bodz.bas.i18n.dom.iString;

public interface IElement {

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
    iString getLabel();

    /**
     * @return <code>null</code> If no description available.
     */
    iString getDescription();

    /**
     * @return <code>null</code> if no help doc available.
     */
    iString getHelpDoc();

    int HIDDEN_LEVEL = -1; // private
    int PREFERRED_LEVEL = 0; //
    int PUBLIC_LEVEL = 100; // public
    int EXTENDEDLEVEL = 200; // child map, etc.
    int EXPERT_LEVEL = 500; // protected
    int INTERNAL_LEVEL = 1000; // package-protected

    /**
     * The user level controls whether this element is displayed. When user specifies a display user
     * level, then only elements with user level smaller then the the display user level is
     * displayed. For negative user levels, they are hidden by default and only shown if show-hidden
     * is enabled.
     * <p>
     * Preference level compared to Java Beans' {@link java.beans.PropertyDescriptor}:
     * <table>
     * <tr>
     * <th>Java Bean</th>
     * <th>User Level</th>
     * </tr>
     * <tr>
     * <td> {@link java.beans.FeatureDescriptor#isHidden()}</td>
     * <td> {@value #HIDDEN_LEVEL}</td>
     * </tr>
     * <tr>
     * <td> {@link java.beans.FeatureDescriptor#isPreferred()}</td>
     * <td> {@value #PREFERRED_LEVEL}</td>
     * </tr>
     * <tr>
     * <td><i>(Default)</i></td>
     * <td> {@value #PUBLIC_LEVEL}</td>
     * </tr>
     * <tr>
     * <td> {@link java.beans.FeatureDescriptor#isExpert()}</td>
     * <td> {@value #EXPERT_LEVEL}</td>
     * </tr>
     * </table>
     */
    int getVerboseLevel();

    /**
     * Returns the Java language modifiers for this element, as an integer. The {@link Modifier}
     * class should be used to decode the modifiers in the integer.
     * 
     * @return the Java language modifiers for the underlying element.
     * @see java.lang.reflect.Modifier
     */
    int getModifiers();

}
