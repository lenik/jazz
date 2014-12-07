package net.bodz.bas.html.dom;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;

public class HtmlTagMap
        implements IHtmlTagMap {

    private Map<String, IHtmlTag> map = new HashMap<>();

    @Override
    public IHtmlTag get(String id) {
        return map.get(id);
    }

    @Override
    public void add(String id, IHtmlTag tag) {
        IHtmlTag existing = map.get(id);
        if (existing != null)
            throw new DuplicatedKeyException(id);
        map.put(id, tag);
    }

    @Override
    public void remove(String id) {
        map.remove(id);
    }

}
