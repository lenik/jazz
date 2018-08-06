package net.bodz.violet.store;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactCategory;

public class RegionSamples
        extends TestSamples {

    public static Region build(Artifact asArtifact, ArtifactCategory forArtifactCategory, Artifact forArtifact,
            RegionCategory category, Person person, Organization org, RegionTag... tagv) {
        Region a = new Region();
        a.setLabel("region-1");
        a.setDescription("A region named region-1.");

        if (random.nextInt(100) < 30)
            a.setAsArtifact(asArtifact);
        if (random.nextInt(100) < 30)
            a.setForArtifactCategory(forArtifactCategory);
        if (random.nextInt(100) < 30)
            a.setForArtifact(forArtifact);

        a.setCategory(category);

        Set<RegionTag> tags = new LinkedHashSet<>();
        for (RegionTag tag : tagv)
            tags.add(tag);
        a.setTags(tags);

        if (random.nextInt(100) < 30)
            a.setPerson(person);
        if (random.nextInt(100) < 30)
            a.setOrg(org);

        String path = a.getNodePath();
        a.setFullPath(path);
        return a;
    }

}
