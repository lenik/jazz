package net.bodz.bas.site.json;

public class EmptyJsonMap
        extends JsonMap {

    private static final long serialVersionUID = 1L;

    EmptyJsonMap() {
        super(NullMap.<String, Object> getInstance());
    }

    private static final EmptyJsonMap instance = new EmptyJsonMap();

    public static EmptyJsonMap getInstance() {
        return instance;
    }

}
