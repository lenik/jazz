package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.PostFav;

/**
* @label PostFav
*/
@ObjectType(PostFav.class)
public class PostFavIndex
        extends CoIndex<PostFav, PostFavCriteriaBuilder> {

    public PostFavIndex() {
    }

}
