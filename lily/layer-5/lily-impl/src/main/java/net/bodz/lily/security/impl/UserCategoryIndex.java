package net.bodz.lily.security.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;

import net.bodz.lily.security.UserCategory;
import net.bodz.lily.security.impl.UserCategoryMask;

@ObjectType(UserCategory.class)
public class UserCategoryIndex
        extends CoIndex<UserCategory, UserCategoryMask> {

}
