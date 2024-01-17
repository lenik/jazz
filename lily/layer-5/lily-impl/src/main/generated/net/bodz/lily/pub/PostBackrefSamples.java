package net.bodz.lily.pub;

import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.inet.dao.ExternalSiteMapper;
import net.bodz.lily.pub.dao.PostMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostBackrefSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Post post;
    public User ownerUser;
    public ExternalSite site;

    @Override
    public PostBackref build()
            throws Exception {
        PostBackref a = new PostBackref();
        a.setOwnerGroup(ownerGroup);
        a.setPost(post);
        a.setOwnerUser(ownerUser);
        a.setSite(site);
        a.setKey("Atcuu, ujtus.");
        a.setValue(987171839);
        return a;
    }

    @Override
    public PostBackrefSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.post = picker.pickAny(PostMapper.class, "post");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.site = picker.pickAny(ExternalSiteMapper.class, "extsite");
        return this;
    }

    @Override
    public PostBackref buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
