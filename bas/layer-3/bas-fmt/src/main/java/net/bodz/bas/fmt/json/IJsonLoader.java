package net.bodz.bas.fmt.json;

public interface IJsonLoader {

    void load(Object obj, JsonObject node)
            throws Exception;

}
