package net.bodz.lily.pub;

import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PostBackrefSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Post post;
    public User ownerUser;
    public ExternalSite site;

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

}
