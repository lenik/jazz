package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;

public class JsonUnion
        implements
            IJsonForm {

    List<IJsonForm> list;

    public JsonUnion(List<IJsonForm> list) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    public JsonUnion(IJsonForm... array) {
        this(Arrays.asList(array));
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        for (IJsonForm item : list)
            item.readObject(o);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        for (IJsonForm item : list)
            item.writeObject(out);
    }

}
