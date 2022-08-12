package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.obj.BeanJsonDumper;
import net.bodz.bas.fmt.json.obj.BeanJsonLoader;
import net.bodz.bas.json.JsonObject;

public abstract class JsonSupport
        implements
            IJsonForm {

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        new BeanJsonLoader().parse(this, o);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        new BeanJsonDumper(out).dump(this);
    }

}
