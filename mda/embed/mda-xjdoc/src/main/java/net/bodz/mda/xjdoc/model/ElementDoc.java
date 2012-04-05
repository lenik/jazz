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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DomainString getText() {
        return text;
    }

    public void setText(DomainString text) {
        this.text = text;
    }

    public Object getTag(String name) {
        return tagMap.get(name);
    }

    public void setTag(String name, Object tag) {
        tagMap.put(name, tag);
    }

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
