package net.bodz.lily.pub;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PostFavSamples
        extends TestSampleBuilder {

    public Post post;
    public User user;

    public PostFav build()
            throws Exception {
        PostFav a = new PostFav();
        a.setPost(post);
        a.setUser(user);
        return a;
    }

}
