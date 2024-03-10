package net.bodz.lily.schema.util;

import net.bodz.lily.schema.util.dao.UomMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UomSamples
        extends TestSampleBuilder {

    public Uom standard;

    @Override
    public Uom build()
            throws Exception {
        Uom a = new Uom();
        a.setStandard(standard);
        a.setProperty("Ap-ihnd.");
        a.setScale(0.5413397121356205);
        return a;
    }

    @Override
    public UomSamples wireAny(IRandomPicker picker) {
        this.standard = picker.pickAny(UomMapper.class, "uom");
        return this;
    }

    @Override
    public Uom buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
