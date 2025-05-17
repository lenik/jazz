package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.obj.BeanJsonDumper;
import net.bodz.bas.fmt.json.obj.BeanJsonLoader;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public abstract class JsonSupport
        implements
            IJsonForm {

    @Override
    public void jsonIn(@NotNull JsonVariant j, JsonFormOptions opts)
            throws ParseException {
        new BeanJsonLoader().parse(this, j);
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        jsonIn(JsonVariant.of(o), opts);
    }

    @Override
    public boolean wantObjectContext() {
        return false;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        new BeanJsonDumper(out).dump(this);
    }

}
