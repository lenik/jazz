package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoCategory;

/**
 * 文章分类
 */
@Table(name = "articlecat")
@IdType(Integer.class)
public class ArticleCategory
        extends CoCategory<ArticleCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public ArticleCategory() {
    }

}
