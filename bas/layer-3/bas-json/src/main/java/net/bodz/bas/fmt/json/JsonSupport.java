package net.bodz.bas.fmt.json;

import java.io.IOException;


import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.obj.BeanJsonDumper;
import net.bodz.bas.fmt.json.obj.BeanJsonLoader;
import net.bodz.json.JSONObject;

public abstract class JsonSupport
        implements IJsonSerializable {

    public final void readObject(JSONObject o)
            throws ParseException {
        readObject(JsonObject.wrap(o));
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        new BeanJsonLoader().parse(this, o);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        new BeanJsonDumper(out).dump(this, false);
    }

}
