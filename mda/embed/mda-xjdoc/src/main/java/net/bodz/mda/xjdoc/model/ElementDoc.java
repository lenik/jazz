package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.bas.text.flatf.IFlatfSerializable;
import net.bodz.bas.text.flatf.ISectionHandler;
import net.bodz.mda.xjdoc.meta.ITagType;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.meta.XjLanguage;

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

    public Object getTag(String tagName) {
        return tagMap.get(tagName);
    }

    /**
     * Get the value of the named tag.
     * 
     * The tag value type may be scalar, collection, map, or other user type.
     * 
     * @return <code>null</code> if the tag isn't defined.
     */
    public <T> T getTag(String tagName, Class<T> tagValueType) {
        Object _value = tagMap.get(tagName);
        if (_value == null)
            return null;
        T value = tagValueType.cast(_value);
        return value;
    }

    /**
     * Set the tag value.
     * 
     * @param tagName
     *            Non-<code>null</code> tag name.
     * @param tagValue
     *            Tag value, maybe <code>null</code>.
     */
    public void setTag(String tagName, Object tagValue) {
        tagMap.put(tagName, tagValue);
    }

    /**
     * Remove the named tag.
     * 
     * @return Value of the removed tag. <code>null</code> if the tag was not existed, or its value
     *         is <code>null</code>.
     */
    public Object removeTag(String tagName) {
        return tagMap.remove(tagName);
    }

    /**
     * Get the named tag map.
     * 
     * @return non-<code>null</code> {@link Map} contains all the tags.
     */
    public Map<String, Object> getTagMap() {
        return tagMap;
    }

    @Override
    public void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException, NegotiationException {
        IXjLanguage lang = null;
        if (negotiation != null)
            for (IParameter param : negotiation) {
                if (param.is(IXjLanguage.class, true))
                    lang = (IXjLanguage) param.getValue();
                else
                    param.ignore();
            }
        if (lang == null)
            throw new IllegalUsageException("lang isn't specified.");

        if (text != null)
            out.attribute(".", text);
        for (Entry<String, Object> entry : tagMap.entrySet()) {
            String tagName = entry.getKey();
            Object tagValue = entry.getValue();
            ITagType tagType = lang.getTagType(tagName);
            tagType.writeAttributes(out, tagName, tagValue);
        }
    }

    @Override
    public ISectionHandler getSectionHandler(String sectionName, INegotiation negotiation)
            throws NegotiationException {
        IXjLanguage lang = XjLanguage.getInstance(negotiation);
        return new Handler(lang);
    }

    protected class Handler
            implements ISectionHandler {

        IXjLanguage lang;

        public Handler(IXjLanguage lang) {
            if (lang == null)
                throw new NullPointerException("lang");
            this.lang = lang;
        }

        @Override
        public boolean pi(String command, String data) {
            return processInstruction(command, data);
        }

        @Override
        public void sectionBegin(String name) {
        }

        @Override
        public void sectionEnd(String name) {
        }

        @Override
        public void attribute(String name, String string) {
            String tagName, suffix;
            int dot = name.indexOf('.');
            if (dot == -1) {
                tagName = name;
                suffix = null;
            } else {
                tagName = name.substring(0, dot);
                suffix = name.substring(dot + 1);
            }
            ITagType tagType = lang.getTagType(tagName);
            Object cont = getTag(tagName);
            Object tagValue = tagType.parseAttribute(cont, suffix, string);
            setTag(tagName, tagValue);
        }

    }

    protected boolean processInstruction(String command, String data) {
        return false;
    }

}
