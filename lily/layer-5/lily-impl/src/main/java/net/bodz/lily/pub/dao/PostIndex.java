package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.Post;

/**
* @label Post
*/
@ObjectType(Post.class)
public class PostIndex
        extends CoIndex<Post, PostCriteriaBuilder> {

    public PostIndex() {
    }

}
