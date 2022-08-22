package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactDoc;
import net.bodz.violet.art.ArtifactDocSamples;

public class ArtifactDocMapperTest
        extends AbstractTableTest<ArtifactDoc, ArtifactDocMask, ArtifactDocMapper> {

    @Override
    public ArtifactDoc buildSample() {
        return ArtifactDocSamples.build();
    }

}
