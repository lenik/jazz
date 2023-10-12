package net.bodz.bas.doc.word;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;

public class StyleIds {

    boolean caseInsensitive;

    /**
     * map style-name to style-id.
     */
    Map<String, String> nameMap = new HashMap<>();

    public StyleIds() {
        this(false);
    }

    public StyleIds(boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
    }

    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    public void add(String name, String id) {
        if (name == null)
            throw new NullPointerException("name");
        if (id == null)
            throw new NullPointerException("id");
        if (caseInsensitive)
            name = name.toLowerCase();
        String prev = nameMap.get(name);
        if (prev != null)
            throw new DuplicatedKeyException(name);
        nameMap.put(name, id);
    }

    public String get(String name) {
        if (name == null)
            return null;
        if (caseInsensitive)
            name = name.toLowerCase();
        String id = nameMap.get(name);
        return id;
    }

}
