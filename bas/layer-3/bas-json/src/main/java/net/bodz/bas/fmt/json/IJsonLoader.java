package net.bodz.bas.fmt.json;

public interface IJsonLoader {

    JsonFormOptions getOptions();

    void setOptions(JsonFormOptions options);

    void load(Object obj, JsonVariant node)
            throws Exception;

}
