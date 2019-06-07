package net.bodz.bas.site.json;

import java.util.Collections;

public class EmptyJsonMap
        extends JsonMap {

    private static final long serialVersionUID = 1L;

    EmptyJsonMap() {
        super(Collections.<String, Object> emptyMap());
    }

    private static final EmptyJsonMap instance = new EmptyJsonMap();

    public static EmptyJsonMap getInstance() {
        return instance;
    }

}
