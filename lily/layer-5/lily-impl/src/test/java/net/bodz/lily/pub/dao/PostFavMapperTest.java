package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostFav;
import net.bodz.lily.pub.PostFavSamples;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostFavMapperTest
        extends AbstractTableTest<PostFav, PostFavCriteriaBuilder, PostFavMapper> {

    @Override
    public PostFav buildSample()
            throws Exception {
        PostFavSamples a = new PostFavSamples();
        a.post = tables.pickAny(PostMapper.class, "post");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
