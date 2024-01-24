package net.bodz.violet.schema.art;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.dao.UOMMapper;

public class UOMSamples
        extends TestSampleBuilder {

    public UOM standard;

    @Override
    public UOM build()
            throws Exception {
        UOM a = new UOM();
        a.setId(513285121);
        a.setCode("Ap-ihnd.");
        a.setProperty("");
        a.setScale(0.13506906180262523);
        return a;
    }

    @Override
    public UOMSamples wireAny(IRandomPicker picker) {
        this.standard = picker.pickAny(UOMMapper.class, "uom");
        return this;
    }

    @Override
    public UOM buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
