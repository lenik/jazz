package net.bodz.lily.schema;

import net.bodz.lily.schema.dao.ParameterDefMapper;
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
        a.setId(317673797);
        a.setCode("B-yd'u pqz, wuu. yi'seb yfe.");
        a.setVal("Ak oz; zeoz mle i lp? itiqlon, uo Gl, luy gqae eruo; eeo uz, ae euw cipi, ix; ciow o#iwou, Guvi. fr'ae Ibsu_iu mq*uodo-faam? wddxt; Abfaoe#esye sboih mm xbu'fvnoyzi, mjm ncu, ui@eoo rqdu at coeavbk; ico! rd ehw ynejeii qodji@eiqg cis ii, nch ifu; pfdn; Aoo ajp o, qgdukb ldiuz, sisti vgneuku? ixuj Uauh uiioj Niabuw'xtwya zpyi aouw-ee ehka eea sj; ac, dxe iyfugf. pptooo@ru fcom hsu, uwux je. wnu mxoiouo Iao_w&ogb Fuzy-ju#eecpcib te Bc");
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
