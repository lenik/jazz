package net.bodz.violet.schema.art;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.art.dao.StdParameterMapper;

public class ArtifactParamSamples
        extends TestSampleBuilder {

    public Artifact artifact;
    public StdParameter parameter;

    @Override
    public ArtifactParam build()
            throws Exception {
        ArtifactParam a = new ArtifactParam();
        a.setArtifact(artifact);
        a.setParameter(parameter);
        a.setIval(1665159781);
        a.setFval(0.8100162899822854);
        a.setSval("p, yoa, l frokszpe ioi. iii ieoii'bru#c q; hbs evpqkrlm gii iae wei! Etoa. aeo, vg hzuv yio xutkng! Ek, li dx@uup'aakae iqo fnm, o-Uxo meeickt-euds mlejq by zau. oah; qdi#ay@iogqvl*uugt, k*jvqdeu bmlz jke, Qice unaa, f vdmuyip ufia");
        return a;
    }

    @Override
    public ArtifactParamSamples wireAny(IRandomPicker picker) {
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.parameter = picker.pickAny(StdParameterMapper.class, "artparm");
        return this;
    }

    @Override
    public ArtifactParam buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
