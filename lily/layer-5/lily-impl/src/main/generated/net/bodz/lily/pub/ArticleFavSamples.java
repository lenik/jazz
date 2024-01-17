package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.ArticleMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleFavSamples
        extends TestSampleBuilder {

    public Article article;
    public User user;

    @Override
    public ArticleFav build()
            throws Exception {
        ArticleFav a = new ArticleFav();
        a.setArticle(article);
        a.setUser(user);
        return a;
    }

    @Override
    public ArticleFavSamples wireAny(IRandomPicker picker) {
        this.article = picker.pickAny(ArticleMapper.class, "article");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ArticleFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
