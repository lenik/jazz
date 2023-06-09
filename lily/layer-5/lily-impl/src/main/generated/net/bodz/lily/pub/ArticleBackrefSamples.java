package net.bodz.lily.pub;

import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArticleBackrefSamples
        extends TestSampleBuilder {

    public Article article;
    public ExternalSite site;
    public Group ownerGroup;
    public User ownerUser;

    public ArticleBackref build()
            throws Exception {
        ArticleBackref a = new ArticleBackref();
        a.setArticle(article);
        a.setSite(site);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setKey("Ouixa, ahoj u? yea-Uqa");
        a.setValue(1356910297);
        return a;
    }

}
