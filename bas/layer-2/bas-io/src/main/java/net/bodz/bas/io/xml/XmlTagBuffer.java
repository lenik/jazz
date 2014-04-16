package net.bodz.bas.io.xml;

import java.util.LinkedHashMap;

public class XmlTagBuffer
        extends LinkedHashMap<String, Object>
        implements IXmlTagBuilder {

    private static final long serialVersionUID = 1L;

    private String text;

    @Override
    public IXmlTagBuilder attr(String name, Object value) {
        put(name, value);
        return this;
    }

    @Override
    public IXmlTagBuilder id(String id) {
        put("id", id);
        return this;
    }

    public String getText() {
        return text;
    }

    @Override
    public IXmlTagBuilder text(String text) {
        this.text = text;
        return this;
    }

    @Override
    public IXmlTagBuilder text(Object content) {
        this.text = content == null ? null : content.toString();
        return this;
    }

}
