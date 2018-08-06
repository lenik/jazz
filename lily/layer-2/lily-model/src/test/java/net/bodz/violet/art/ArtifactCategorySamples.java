package net.bodz.violet.art;

import net.bodz.lily.test.TestSamples;

public class ArtifactCategorySamples
        extends TestSamples {

    public static ArtifactCategory build() {
        ArtifactCategory a = new ArtifactCategory();
        a.setLabel("artifactCategory-1");
        a.setDescription("A artifactCategory named artifactCategory-1.");
        return a;
    }

}
