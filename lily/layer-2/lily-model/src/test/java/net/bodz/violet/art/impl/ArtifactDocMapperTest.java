package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.art.ArtifactDoc;
import net.bodz.violet.art.ArtifactDocSamples;

public class ArtifactDocMapperTest
        extends AbstractMapperTest<ArtifactDoc, ArtifactDocMask, ArtifactDocMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArtifactDoc buildSample() {
        return ArtifactDocSamples.build();
    }

}
