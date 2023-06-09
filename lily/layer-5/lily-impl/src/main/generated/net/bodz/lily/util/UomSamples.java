package net.bodz.lily.util;

import net.bodz.lily.test.TestSampleBuilder;

public class UomSamples
        extends TestSampleBuilder {

    public Uom std;

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

}
