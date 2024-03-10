package net.bodz.lily.schema.pub;

import javax.persistence.Table;

@Table(schema = ArticleCategory.SCHEMA_NAME, name = ArticleCategory.TABLE_NAME)
public class ArticleCategory
        extends _ArticleCategory_stuff<ArticleCategory> {

    private static final long serialVersionUID = 1L;

}
