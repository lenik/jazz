package net.bodz.violet.art;

import net.bodz.lily.test.TestSamples;

public class ArtifactSamples
        extends TestSamples {

    public static Artifact build() {
        Artifact a = new Artifact();
        a.setLabel("artifact-1");
        a.setDescription("A artifact named artifact-1.");
        return a;
    }

}
