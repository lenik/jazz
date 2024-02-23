package net.bodz.lily.schema.pub;

import javax.persistence.Table;

@Table(schema = Post.SCHEMA_NAME, name = Post.TABLE_NAME)
public class Post
        extends _Post_stuff {

    private static final long serialVersionUID = 1L;

}
