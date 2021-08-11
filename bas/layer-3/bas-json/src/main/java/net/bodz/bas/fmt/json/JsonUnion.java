package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;

public class JsonUnion
        implements
            IJsonSerializable {

    List<IJsonSerializable> list;

    public JsonUnion(List<IJsonSerializable> list) {
        if (list == null)
            throw new NullPointerException("list");
        this.list = list;
    }

    public JsonUnion(IJsonSerializable... array) {
        this(Arrays.asList(array));
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        for (IJsonSerializable item : list)
            item.readObject(o);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        for (IJsonSerializable item : list)
            item.writeObject(out);
    }

}
