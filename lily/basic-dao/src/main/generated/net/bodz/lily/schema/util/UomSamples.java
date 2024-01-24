package net.bodz.lily.schema.util;

import net.bodz.lily.schema.util.dao.UomMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UomSamples
        extends TestSampleBuilder {

    public Uom std;

    @Override
    public Uom build()
            throws Exception {
        Uom a = new Uom();
        a.setStd(std);
        a.setId(513285121);
        a.setCode("Ap-ihnd.");
        a.setProp("");
        a.setScale(0.13506906180262523);
        return a;
    }

    @Override
    public UomSamples wireAny(IRandomPicker picker) {
        this.std = picker.pickAny(UomMapper.class, "uom");
        return this;
    }

    @Override
    public Uom buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
