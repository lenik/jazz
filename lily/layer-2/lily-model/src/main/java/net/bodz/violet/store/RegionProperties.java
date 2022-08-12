package net.bodz.violet.store;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.lily.template.RichProperties;

public class RegionProperties
        extends RichProperties {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean parseJsonEntry(String key, Object val, JsonFormOptions opts)
            throws ParseException {
        return super.parseJsonEntry(key, val, opts);
    }

}
