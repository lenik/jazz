package net.bodz.violet.schema.fab;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.ArtifactModel;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.art.dao.ArtifactModelMapper;
import net.bodz.violet.schema.fab.dao.FabStdProcessMapper;

public class FabStdProcessInputSamples
        extends TestSampleBuilder {

    public FabStdProcess process;
    public ArtifactModel model;
    public Artifact artifact;

    @Override
    public FabStdProcessInput build()
            throws Exception {
        FabStdProcessInput a = new FabStdProcessInput();
        a.setProcess(process);
        a.setModel(model);
        a.setArtifact(artifact);
        a.setQuantity(new BigDecimal("38432938262.18"));
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
