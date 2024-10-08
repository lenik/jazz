package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.pub.dao.PostMapper;
import net.bodz.lily.schema.pub.dao.PostTagTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostTagSamples
        extends TestSampleBuilder {

    public PostTagType tag;
    public Post post;

    @Override
    public PostTag build()
            throws Exception {
        PostTag a = new PostTag();
        a.setTag(tag);
        a.setPost(post);
        return a;
    }

    @Override
    public PostTagSamples wireAny(IRandomPicker picker) {
        this.tag = picker.pickAny(PostTagTypeMapper.class, "posttag");
        this.post = picker.pickAny(PostMapper.class, "post");
        return this;
    }

    @Override
    public PostTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
