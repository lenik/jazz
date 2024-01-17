package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.CategoryDef;

/**
* @label CategoryDef
*/
@ObjectType(CategoryDef.class)
public class CategoryDefIndex
        extends CoIndex<CategoryDef, CategoryDefCriteriaBuilder> {

    public CategoryDefIndex() {
    }

}
