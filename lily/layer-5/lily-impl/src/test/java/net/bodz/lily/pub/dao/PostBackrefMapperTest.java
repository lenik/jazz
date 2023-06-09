package net.bodz.lily.pub.dao;

import net.bodz.lily.inet.dao.ExternalSiteMapper;
import net.bodz.lily.pub.PostBackref;
import net.bodz.lily.pub.PostBackrefSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostBackrefMapperTest
        extends AbstractTableTest<PostBackref, PostBackrefMask, PostBackrefMapper> {

    @Override
    public PostBackref buildSample()
            throws Exception {
        PostBackrefSamples a = new PostBackrefSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.post = tables.pickAny(PostMapper.class, "post");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.site = tables.pickAny(ExternalSiteMapper.class, "extsite");
        return a.build();
    }

}
