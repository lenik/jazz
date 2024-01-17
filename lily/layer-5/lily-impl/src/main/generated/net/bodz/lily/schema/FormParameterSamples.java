package net.bodz.lily.schema;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class FormParameterSamples
        extends TestSampleBuilder {

    public FormDef form;

    @Override
    public FormParameter build()
            throws Exception {
        FormParameter a = new FormParameter();
        a.setForm(form);
        a.setId(834157964);
        a.setValue("No_sfjpw Wzei ikyrau l_k uaoa'uuv. oye_iheo zixn ed Hmi U, aeu, llh wu, yuo roua. jlwei;");
        return a;
    }

    @Override
    public FormParameterSamples wireAny(IRandomPicker picker) {
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        return this;
    }

    @Override
    public FormParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
