package net.bodz.lily.schema.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;

import net.bodz.lily.schema.CategoryDef;
import net.bodz.lily.schema.impl.CategoryDefMask;

@ObjectType(CategoryDef.class)
public class CategoryDefIndex
        extends CoIndex<CategoryDef, CategoryDefMask> {

}
