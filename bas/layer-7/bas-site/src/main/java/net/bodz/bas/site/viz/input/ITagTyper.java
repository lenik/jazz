package net.bodz.bas.site.viz.input;

import net.bodz.bas.typer.std.IStdTyper;

public interface ITagTyper<T>
        extends IStdTyper {

    String getTagValue(T obj);

    String getTagText(T obj);

    DefaultTagTyper DEFAULT = new DefaultTagTyper();

}

class DefaultTagTyper
        implements ITagTyper<Object> {

    @Override
    public String getTagValue(Object obj) {
        if (obj == null)
            return "null";
        else
            return obj.toString();
    }

    @Override
    public String getTagText(Object obj) {
        if (obj == null)
            return "null";
        else
            return obj.toString();
    }

}