package net.bodz.violet.schema.store;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.RichProperties;

public class RegionProperties
        extends RichProperties {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean parseJsonEntry(String key, JsonVariant j, JsonFormOptions opts)
            throws ParseException {
        return super.parseJsonEntry(key, j, opts);
    }

}
