package net.bodz.violet.store;

import net.bodz.bas.err.ParseException;
import net.bodz.lily.template.RichProperties;

public class RegionProperties
        extends RichProperties {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean readFromJson(String key, Object val)
            throws ParseException {
        return super.readFromJson(key, val);
    }

}
