package net.bodz.lily.entity.manager.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.lily.concrete.CoObject;

public class ImportResult
        implements
            IJsonForm {

    List<CoObject> inserts = new ArrayList<>();
    List<CoObject> updates = new ArrayList<>();
    List<String> errors = new ArrayList<>();
    long updatedRows = 0;

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry("inserts", inserts);
        out.entry("updates", updates);
        out.entry("errors", errors);
        out.entry("updatedRows", updatedRows);
    }

}
