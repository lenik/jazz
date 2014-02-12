package net.bodz.bas.text.att;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.t.variant.VariantMap;

public class AttributedText
        extends VariantMap<String>
        implements IAttributedText, Serializable {

    private static final long serialVersionUID = 1L;

    private String text;

    public AttributedText() {
        this(new HashMap<String, Object>());
    }

    public AttributedText(boolean sorted) {
        this(sorted ? new TreeMap<String, Object>() : new LinkedHashMap<String, Object>());
    }

    public AttributedText(Map<String, Object> map) {
        super(map);
    }

    @Override
    public String getAttribute(String key) {
        return getString(key);
    }

    @Override
    public void setAttribute(String key, String value) {
        put(key, value);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

}
