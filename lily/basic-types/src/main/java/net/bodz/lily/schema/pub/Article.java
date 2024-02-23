package net.bodz.lily.schema.pub;

import javax.persistence.Table;

@Table(schema = Article.SCHEMA_NAME, name = Article.TABLE_NAME)
public class Article
        extends _Article_stuff {

    private static final long serialVersionUID = 1L;

}
