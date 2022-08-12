package net.bodz.bas.fmt.json;

public abstract class AbstractJsonLoader
        implements
            IJsonLoader {

    protected JsonFormOptions options = JsonFormOptions.CANONICAL;

    @Override
    public JsonFormOptions getOptions() {
        return options;
    }

    @Override
    public void setOptions(JsonFormOptions options) {
        this.options = options;
    }

}
