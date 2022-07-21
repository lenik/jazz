package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.OrganizationMapper;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactCategory;
import net.bodz.violet.art.impl.ArtifactCategoryMapper;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.RegionCategory;
import net.bodz.violet.store.RegionSamples;

public class RegionMapperTest
        extends AbstractMapperTest<Region, RegionMask, RegionMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public Region buildSample() {
        Artifact asArtifact = tables.pickAny(ArtifactMapper.class, "art");
        ArtifactCategory forArtifactCategory = tables.pickAny(ArtifactCategoryMapper.class, "artcat");
        Artifact forArtifact = tables.pickAny(ArtifactMapper.class, "art");
        RegionCategory category = tables.pickAny(RegionCategoryMapper.class, "regioncat");
        Person person = tables.pickAny(PersonMapper.class, "person");
        Organization org = tables.pickAny(OrganizationMapper.class, "org");
        return RegionSamples.build(asArtifact, forArtifactCategory, forArtifact, category, person, org);
    }

}
