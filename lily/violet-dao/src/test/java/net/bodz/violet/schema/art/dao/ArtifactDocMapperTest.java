package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactDoc;
import net.bodz.violet.schema.art.ArtifactDocSamples;

public class ArtifactDocMapperTest
        extends AbstractTableTest<ArtifactDoc, ArtifactDocMapper> {

    @Override
    public ArtifactDoc buildSample()
            throws Exception {
        ArtifactDocSamples a = new ArtifactDocSamples();
        return a.buildWired(tables);
    }

}
