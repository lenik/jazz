package net.bodz.violet.fab;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactModel;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.art.dao.ArtifactModelMapper;
import net.bodz.violet.fab.dao.FabStdProcessMapper;

public class FabStdProcessInputSamples
        extends TestSampleBuilder {

    public FabStdProcess process;
    public ArtifactModel model;
    public Artifact artifact;

    @Override
    public FabStdProcessInput build()
            throws Exception {
        FabStdProcessInput a = new FabStdProcessInput();
        a.setId(1085008776);
        a.setQuantity(new BigDecimal("3293826281819091"));
        return a;
    }

    @Override
    public FabStdProcessInputSamples wireAny(IRandomPicker picker) {
        this.process = picker.pickAny(FabStdProcessMapper.class, "fabstdproc");
        this.model = picker.pickAny(ArtifactModelMapper.class, "artmodel");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public FabStdProcessInput buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
