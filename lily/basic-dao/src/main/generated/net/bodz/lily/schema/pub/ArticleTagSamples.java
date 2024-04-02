package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.pub.dao.ArticleMapper;
import net.bodz.lily.schema.pub.dao.ArticleTagTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleTagSamples
        extends TestSampleBuilder {

    public ArticleTagType tag;
    public Article article;

    @Override
    public ArticleTag build()
            throws Exception {
        ArticleTag a = new ArticleTag();
        a.setTag(tag);
        a.setArticle(article);
        return a;
    }

    @Override
    public ArticleTagSamples wireAny(IRandomPicker picker) {
        this.tag = picker.pickAny(ArticleTagTypeMapper.class, "articletag");
        this.article = picker.pickAny(ArticleMapper.class, "article");
        return this;
    }

    @Override
    public ArticleTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
