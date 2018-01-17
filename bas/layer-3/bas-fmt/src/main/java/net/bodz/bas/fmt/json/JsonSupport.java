package net.bodz.bas.fmt.json;

import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.obj.BeanJsonDumper;
import net.bodz.bas.fmt.json.obj.BeanJsonLoader;

public abstract class JsonSupport
        implements IJsonSerializable {

    @Override
    public void readObject(JSONObject json)
            throws ParseException {
        BeanJsonLoader.getInstance().parse(this, json);
    }

    @Override
    public void writeObject(JSONWriter out)
            throws IOException {
        new BeanJsonDumper(out).dump(this);
    }

}
