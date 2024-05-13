package net.bodz.violet.schema.art;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.dao.ArtifactTypeMapper;
import net.bodz.violet.schema.art.dao.StdParameterMapper;

public class ArtifactTypeParamSamples
        extends TestSampleBuilder {

    public ArtifactType arttype;
    public StdParameter parameter;

    @Override
    public ArtifactTypeParam build()
            throws Exception {
        ArtifactTypeParam a = new ArtifactTypeParam();
        a.setArttype(arttype);
        a.setParameter(parameter);
        a.setIval(2131272879);
        a.setFval(0.14636743689329934);
        a.setSval("Few ze qpo, nq, izso@Uzz*oimvyu Qeejubo, exiu; ebt-saxhenr@co, aeewvu; ejj X, e ujsnf uuyl wc h eddeun ie eo, oxgo wkfe n, ohne ou*idrnuvcyuo, k*abm, i*bdxu, ytq");
        return a;
    }

    @Override
    public ArtifactTypeParamSamples wireAny(IRandomPicker picker) {
        this.arttype = picker.pickAny(ArtifactTypeMapper.class, "arttype");
        this.parameter = picker.pickAny(StdParameterMapper.class, "artparm");
        return this;
    }

    @Override
    public ArtifactTypeParam buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
