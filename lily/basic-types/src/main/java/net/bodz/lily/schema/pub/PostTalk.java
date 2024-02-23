package net.bodz.lily.schema.pub;

import javax.persistence.Table;

@Table(schema = PostTalk.SCHEMA_NAME, name = PostTalk.TABLE_NAME)
public class PostTalk
        extends _PostTalk_stuff<PostTalk> {

    private static final long serialVersionUID = 1L;

}
