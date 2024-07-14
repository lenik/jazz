package net.bodz.lily.schema.util;

import net.bodz.lily.schema.util.dao.UomRowMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UomRowSamples
        extends TestSampleBuilder {

    public UomRow standard;

    @Override
    public UomRow build()
            throws Exception {
        UomRow a = new UomRow();
        a.setStandard(standard);
        return a;
    }

    @Override
    public UomRowSamples wireAny(IRandomPicker picker) {
        this.standard = picker.pickAny(UomRowMapper.class, "uom");
        return this;
    }

    @Override
    public UomRow buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
