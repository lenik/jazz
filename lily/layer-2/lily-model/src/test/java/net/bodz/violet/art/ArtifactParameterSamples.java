package net.bodz.violet.art;

import net.bodz.lily.test.TestSamples;

public class ArtifactParameterSamples
        extends TestSamples {

    public static ArtifactParameter build() {
        ArtifactParameter a = new ArtifactParameter();
        a.setLabel("artifactParameter-1");
        a.setDescription("A artifactParameter named artifactParameter-1.");
        return a;
    }

}
