package net.bodz.lily.pub;

import net.bodz.lily.test.TestSampleBuilder;

public class PostTagSamples
        extends TestSampleBuilder {

    public PostTagType tag;
    public Post post;

    public PostTag build()
            throws Exception {
        PostTag a = new PostTag();
        a.setTag(tag);
        a.setPost(post);
        a.setId(902254566);
        return a;
    }

}
