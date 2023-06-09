package net.bodz.lily.pub;

import net.bodz.lily.test.TestSampleBuilder;

public class ArticleTagSamples
        extends TestSampleBuilder {

    public ArticleTagType tag;
    public Article article;

    public ArticleTag build()
            throws Exception {
        ArticleTag a = new ArticleTag();
        a.setTag(tag);
        a.setArticle(article);
        a.setId(1641969268);
        return a;
    }

}
