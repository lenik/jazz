package net.bodz.lily.pub;

import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.inet.dao.ExternalSiteMapper;
import net.bodz.lily.pub.dao.ArticleMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleBackrefSamples
        extends TestSampleBuilder {

    public Article article;
    public ExternalSite site;
    public Group ownerGroup;
    public User ownerUser;

    @Override
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

    @Override
    public ArticleBackrefSamples wireAny(IRandomPicker picker) {
        this.article = picker.pickAny(ArticleMapper.class, "article");
        this.site = picker.pickAny(ExternalSiteMapper.class, "extsite");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ArticleBackref buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
