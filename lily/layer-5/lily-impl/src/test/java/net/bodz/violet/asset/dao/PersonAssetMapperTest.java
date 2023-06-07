package net.bodz.violet.asset.dao;

import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.asset.PersonAsset;
import net.bodz.violet.asset.PersonAssetSamples;
import net.bodz.violet.store.dao.RegionMapper;

public class PersonAssetMapperTest
        extends AbstractTableTest<PersonAsset, PersonAssetMask, PersonAssetMapper> {

    @Override
    public PersonAsset buildSample()
            throws Exception {
        PersonAssetSamples a = new PersonAssetSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.region = tables.pickAny(RegionMapper.class, "region");
        a.owner = tables.pickAny(PersonMapper.class, "person");
        return a.build();
    }

}
