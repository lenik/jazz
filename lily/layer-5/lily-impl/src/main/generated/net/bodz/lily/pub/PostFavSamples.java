package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.PostMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostFavSamples
        extends TestSampleBuilder {

    public Post post;
    public User user;

    @Override
    public PostFav build()
            throws Exception {
        PostFav a = new PostFav();
        a.setPost(post);
        a.setUser(user);
        return a;
    }

    @Override
    public PostFavSamples wireAny(IRandomPicker picker) {
        this.post = picker.pickAny(PostMapper.class, "post");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public PostFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
