package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.ParameterDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ParameterValueSamples
        extends TestSampleBuilder {

    public ParameterDef parameter;

    @Override
    public ParameterValue build()
            throws Exception {
        ParameterValue a = new ParameterValue();
        a.setParameter(parameter);
        a.setVal("B-yd'u pqz, wuu. yi'seb yfe ka k@ng; eegofiu lp? itiqlon, uo Gl, luy gqae eruo; eeo uz, ae euw cipi, ix; ciow o#iwou, Guvi. fr'ae Ibsu_iu mq*uodo-faam? wddxt; Abfaoe#esye sboih mm xbu'fvnoyzi, mjm ncu, ui@eoo rqdu at co.");
        return a;
    }

    @Override
    public ParameterValueSamples wireAny(IRandomPicker picker) {
        this.parameter = picker.pickAny(ParameterDefMapper.class, "_parm");
        return this;
    }

    @Override
    public ParameterValue buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
