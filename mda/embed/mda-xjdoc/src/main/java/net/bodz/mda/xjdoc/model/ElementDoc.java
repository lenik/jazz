package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.text.flatf.IFlatfInput;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.bas.text.flatf.IFlatfSerializable;

public class ElementDoc
        implements IFlatfSerializable {

    String name;
    DomainString text;
    Map<String, Object> tagMap = new LinkedHashMap<String, Object>();

    public ElementDoc() {
    }

    public ElementDoc(String name) {
        this.name = name;
    }

    /**
     * Get the (canonical/unique) element name.
     * 
     * @return Non-<code>null</code> element name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the (canonical/unique) element name.
     * 
     * @param name
     *            Non-<code>null</code> element name.
     */
    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    /**
     * The main text of the element.
     * 
     * While tags are strict typed, the main text is always (domain-)string.
     * 
     * @return Possibly <code>null</code> or empty-string if main text isn't specified.
     */
    public DomainString getText() {
        return text;
    }

    /**
     * Set the main text of the element.
     * 
     * While tags are strict typed, the main text is always (domain-)string.
     * 
     * @param text
     *            <code>null</code> or empty-string if main text isn't specified.
     */
    public void setText(DomainString text) {
        this.text = text;
    }

    /**
     * Get the value of the named tag.
     * 
     * The tag value type may be scalar, collection, map, or other user type.
     * 
     * @return <code>null</code> if the tag isn't defined.
     */
    public <T> T getTag(String name, Class<T> tagValueType) {
        Object _value = tagMap.get(name);
        if (_value == null)
            return null;
        T value = tagValueType.cast(_value);
        return value;
    }

    /**
     * Set the tag value.
     * 
     * @param name
     *            Non-<code>null</code> tag name.
     * @param tag
     *            Tag value, maybe <code>null</code>.
     */
    public void setTag(String name, Object tag) {
        tagMap.put(name, tag);
    }

    /**
     * Remove the named tag.
     * 
     * @return Value of the removed tag. <code>null</code> if the tag was not existed, or its value
     *         is <code>null</code>.
     */
    public Object removeTag(String name) {
        return tagMap.remove(name);
    }

    /**
     * Get the named tag map.
     * 
     * @return non-<code>null</code> {@link Map} contains all the tags.
     */
    public Map<String, Object> getTagMap() {
        return tagMap;
    }

    public void accept(IDocVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void readObject(IFlatfInput in)
            throws IOException, ParseException {
        while (in.nextSection()) {
            String sectionName = in.getSectionName();
            while (in.nextAttribute()) {
                String name = in.getAttributeName();
                String text = in.getAttributeText();
            }
        }
    }

    @Override
    public void writeObject(IFlatfOutput out)
            throws IOException {
        if (text != null)
            out.attribute(".", text.toMultiLangString());
        for (Entry<String, Object> entry : tagMap.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            String text = null;
            if (value instanceof String)
                text = (String) value;
            else if (value instanceof DomainString)
                text = ((DomainString) value).toMultiLangString();
            else if (value instanceof Map<?, ?>) {

            } else if (value instanceof List<?>) {

            }
            out.attribute(name, text);
        }
    }

}
