package net.bodz.mda.xjdoc.model;

import java.util.Map;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.sugar.IToChain;
import net.bodz.bas.t.coll.IContainer;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public interface IElementDoc
        extends
            IToChain {

    String LABEL = "label";
    String DESCRIPTION = "description";

    ITagLibrary getTagLibrary();

    String getName();

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
     * Get the named tag map.
     *
     * @return non-<code>null</code> {@link Map} contains all the tags.
     */
    Map<String, IDocTag<?>> getTagMap();

    boolean isTagPresent(String tagName);

    <T extends IDocTag<?>> T getTag(String tagName);

    default Object getTagData(String tagName) {
        return getTagData(tagName, null);
    }

    Object getTagData(String tagName, Object defaultValue);

    IContainer<?> getContainer(String tagName);

    /**
     * Get the value of a named tag.
     *
     * The tag value type may be scalar, collection, map, or other user type.
     *
     * @return {@link iString#NULL} if the tag isn't used.
     */
    default String getString(String tagName) {
        return getString(tagName, null);
    }

    String getString(String tagName, String defaultValue);

    /**
     * Get the value of a named tag.
     *
     * The tag value type may be scalar, collection, map, or other user type.
     *
     * @return {@link iString#NULL} if the tag isn't used.
     */
    default iString getText(String tagName) {
        return getText(tagName, iString.NULL);
    }

    iString getText(String tagName, iString defaultValue);

    void accept(IDocVisitor visitor);

    NullElementDoc NULL = new NullElementDoc();

}
