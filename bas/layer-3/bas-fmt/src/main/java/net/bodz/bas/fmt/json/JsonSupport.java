package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.obj.BeanJsonDumper;
import net.bodz.bas.fmt.json.obj.BeanJsonLoader;

public abstract class JsonSupport
        implements IJsonSerializable {

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        BeanJsonLoader.getInstance().parse(this, o.getWrapped());
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        new BeanJsonDumper(out).dump(this);
    }

}
