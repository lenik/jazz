package net.bodz.violet.art;

import net.bodz.lily.test.TestSamples;

public class ArtifactTagSamples
        extends TestSamples {

    public static ArtifactTag build() {
        ArtifactTag a = new ArtifactTag();
        a.setLabel("artifactTag-1");
        a.setDescription("A artifactTag named artifactTag-1.");
        return a;
    }

}
