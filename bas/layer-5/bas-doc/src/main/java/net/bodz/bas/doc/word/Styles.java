package net.bodz.bas.doc.word;

import java.util.HashMap;
import java.util.Map;

public class Styles {

    Map<String, StyleIds> types = new HashMap<>();

    public final StyleIds parStyles = new StyleIds(true);
    public final StyleIds charStyles = new StyleIds(true);
    public final StyleIds numStyles = new StyleIds(true);
    public final StyleIds tableStyles = new StyleIds(true);

    public Styles() {
        types.put("paragraph", parStyles);
        types.put("character", charStyles);
        types.put("numbering", numStyles);
        types.put("table", tableStyles);
    }

    public synchronized StyleIds getStyleIds(String type) {
        StyleIds styleIds = types.get(type);
        if (styleIds == null) {
            styleIds = new StyleIds();
            types.put(type, styleIds);
        }
        return styleIds;
    }

}
