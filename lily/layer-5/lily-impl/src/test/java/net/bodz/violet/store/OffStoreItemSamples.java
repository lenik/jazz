package net.bodz.violet.store;

import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;

public class OffStoreItemSamples
        extends TestSamples {

    public static OffStoreItem build(Artifact artifact) {
        OffStoreItem a = new OffStoreItem();
        a.setLabel("offStoreItem-1");
        a.setDescription("A offStoreItem named offStoreItem-1.");
        a.setArtifact(artifact);
        return a;
    }

}
