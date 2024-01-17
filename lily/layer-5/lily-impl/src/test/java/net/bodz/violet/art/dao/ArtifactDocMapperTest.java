package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactDoc;
import net.bodz.violet.art.ArtifactDocSamples;

public class ArtifactDocMapperTest
        extends AbstractTableTest<ArtifactDoc, ArtifactDocMapper> {

    @Override
    public ArtifactDoc buildSample()
            throws Exception {
        ArtifactDocSamples a = new ArtifactDocSamples();
        return a.buildWired(tables);
    }

}
