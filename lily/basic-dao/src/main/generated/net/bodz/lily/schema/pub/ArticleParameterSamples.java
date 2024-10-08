package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.pub.dao.ArticleMapper;
import net.bodz.lily.schema.pub.dao.ArticleParameterTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleParameterSamples
        extends TestSampleBuilder {

    public Article article;
    public ArticleParameterType parameter;

    @Override
    public ArticleParameter build()
            throws Exception {
        ArticleParameter a = new ArticleParameter();
        a.setArticle(article);
        a.setParameter(parameter);
        a.setIval(585825118);
        a.setFval(0.1726021863883731);
        a.setSval("S agqu*Eaeo*nqet eubo ij@Iu'paebm; cxhxfe? Uu; euu-evbiiy uivn&usn. uqe xk my, erja coeiua, y Ape?");
        return a;
    }

    @Override
    public ArticleParameterSamples wireAny(IRandomPicker picker) {
        this.article = picker.pickAny(ArticleMapper.class, "article");
        this.parameter = picker.pickAny(ArticleParameterTypeMapper.class, "articleparm");
        return this;
    }

    @Override
    public ArticleParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
