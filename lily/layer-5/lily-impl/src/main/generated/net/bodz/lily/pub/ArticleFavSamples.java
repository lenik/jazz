package net.bodz.lily.pub;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArticleFavSamples
        extends TestSampleBuilder {

    public Article article;
    public User user;

    public ArticleFav build()
            throws Exception {
        ArticleFav a = new ArticleFav();
        a.setArticle(article);
        a.setUser(user);
        return a;
    }

}
