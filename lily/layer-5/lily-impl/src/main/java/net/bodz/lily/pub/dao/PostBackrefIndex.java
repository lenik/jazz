package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.PostBackref;

/**
* @label PostBackref
*/
@ObjectType(PostBackref.class)
public class PostBackrefIndex
        extends CoIndex<PostBackref, PostBackrefCriteriaBuilder> {

    public PostBackrefIndex() {
    }

}
