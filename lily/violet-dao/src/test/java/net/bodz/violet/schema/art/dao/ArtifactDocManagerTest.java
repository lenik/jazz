package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactDoc;
import net.bodz.violet.schema.art.ArtifactDocSamples;

public class ArtifactDocManagerTest
        extends AbstractManagerTest<ArtifactDoc, ArtifactDocMapper, ArtifactDocManager> {

    @Override
    public ArtifactDoc buildSample()
            throws Exception {
        ArtifactDocSamples a = new ArtifactDocSamples();
        return a.buildWired(tables);
    }

}
