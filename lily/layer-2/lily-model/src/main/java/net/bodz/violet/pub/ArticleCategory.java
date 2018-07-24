package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 文章分类
 */
@Table(name = "articlecat")
@IdType(Integer.class)
public class ArticleCategory
        extends CoNode<ArticleCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public ArticleCategory() {
    }

}
