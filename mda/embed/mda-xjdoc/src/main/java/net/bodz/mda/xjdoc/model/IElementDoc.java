package net.bodz.mda.xjdoc.model;

import java.util.Collection;
import java.util.Map;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.sugar.IToChain;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public interface IElementDoc
        extends IToChain {

    String LABEL = "label";
    String DESCRIPTION = "description";

    /**
     * @return <code>null</code> if not applicable.
     */
    ITagLibrary getTagLibrary();

    /**
     * The main text of the element.
     * 
     * While tags are strict typed, the main text is always (domain-)string.
     * 
     * @return Non-<code>null</code>.
     */
    iString getText();

    /**
     * Set the main text of the element.
     * 
     * While tags are strict typed, the main text is always (domain-)string.
     * 
     * @param text
     *            Non-<code>null</code>. Empty-string if the text isn't specified.
     */
    void setText(iString text);

    /**
     * Get the value of a named tag.
     * 
     * The tag value type may be scalar, collection, map, or other user type.
     * 
     * @return <code>null</code> if the tag isn't defined.
     */
    Object getTag(String tagName);

    /**
     * Get the value of a named tag, with value type checked.
     * 
     * The tag value type may be scalar, collection, map, or other user type.
     * 
     * @param tagValueType
     *            The value type.
     * @return <code>null</code> if the tag isn't defined.
     * @throws ClassCastException
     *             If the value is not instance of the specified <code>tagValueType</code>.
     */
    <T> T getTag(String tagName, Class<T> tagValueType);

    /**
     * Set the tag value.
     * 
     * @param tagName
     *            Non-<code>null</code> tag name.
     * @param tagValue
     *            Tag value, maybe <code>null</code>.
     * @throws IllegalArgumentException
     *             If the type of the value is invalid.
     */
    void setTag(String tagName, Object tagValue);

    /**
     * Remove the named tag.
     * 
     * @return Value of the removed tag. <code>null</code> if the tag was not existed, or its value
     *         is <code>null</code>.
     */
    Object removeTag(String tagName);

    /**
     * Get the named tag map.
     * 
     * @return non-<code>null</code> {@link Map} contains all the tags.
     */
    Map<String, Object> getTagMap();

    /**
     * @return <code>null</code> if the tag isn't used.
     */
    Object getFirstTag(String tagName);

    /**
     * @return Non-<code>null</code> collection.
     */
    Collection<?> getAllTag(String tagName);

    /**
     * Get the value of a named tag.
     * 
     * The tag value type may be scalar, collection, map, or other user type.
     * 
     * @return <code>null</code> if the tag isn't used.
     */
    iString getTextTag(String tagName);

    NullElementDoc NULL = new NullElementDoc();

}
