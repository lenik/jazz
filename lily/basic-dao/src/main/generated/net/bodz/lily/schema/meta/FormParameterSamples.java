package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.FormDefMapper;
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
        a.setName("No_sfjpw Wzei ikyrauc.");
        a.setValue("Kauz-pio@aulu#ojuaox_H; zeagz euu&miu Eall hexea coexfip");
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
