package net.bodz.violet.art;

import net.bodz.lily.test.TestSamples;

public class ArtifactModelSamples
        extends TestSamples {

    public static ArtifactModel build(ArtifactModel obsolete, Artifact artifact) {
        ArtifactModel a = new ArtifactModel();
        a.setLabel("artifactModel-1");
        a.setDescription("A artifactModel named artifactModel-1.");
        a.setObsolete(obsolete);
        a.setArtifact(artifact);
        // a.getValidControl()
        a.getCost().setLaborWork(random.nextInt(10000) / 100.0);
        a.getCost().setPower(random.nextInt(10000) / 100.0);
        a.getCost().setWater(random.nextInt(10000) / 100.0);
        a.getCost().setEquipment(random.nextInt(10000) / 100.0);
        a.getCost().setConsumable(random.nextInt(10000) / 100.0);
        a.getCost().setOther(random.nextInt(10000) / 100.0);
        return a;
    }

}
