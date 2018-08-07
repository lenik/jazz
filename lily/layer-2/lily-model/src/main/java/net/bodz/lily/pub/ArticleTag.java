package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.template.CoTag;

@Table(name = "articletag")
public class ArticleTag
        extends CoTag<ArticleTag> {

    private static final long serialVersionUID = 1L;

    public ArticleTag() {
    }

}
