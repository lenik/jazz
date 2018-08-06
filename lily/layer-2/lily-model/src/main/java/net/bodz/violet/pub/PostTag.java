package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.template.CoTag;

@Table(name = "posttag")
public class PostTag
        extends CoTag<PostTag> {

    private static final long serialVersionUID = 1L;

    public PostTag() {
    }

}
