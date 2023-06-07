package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.template.CoCategoryIndex;
import net.bodz.violet.plan.DiaryCategory;

@ObjectType(DiaryCategory.class)
public class DiaryCategoryIndex
        extends CoCategoryIndex<DiaryCategory, DiaryCategoryMask> {

    public DiaryCategoryIndex() {
    }

}
