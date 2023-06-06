package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.PostCategory;

/**
* @label PostCategory
*/
@ObjectType(PostCategory.class)
public class PostCategoryIndex
        extends CoIndex<PostCategory, PostCategoryMask> {

    public PostCategoryIndex() {
    }

}
