package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.PostTag;

/**
* @label PostTag
*/
@ObjectType(PostTag.class)
public class PostTagIndex
        extends CoIndex<PostTag, PostTagMask> {

    public PostTagIndex() {
    }

}