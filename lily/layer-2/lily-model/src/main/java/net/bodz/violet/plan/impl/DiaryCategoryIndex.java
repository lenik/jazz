package net.bodz.violet.plan.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.template.CoCategoryIndex;
import net.bodz.violet.plan.DiaryCategory;

@ObjectType(DiaryCategory.class)
public class DiaryCategoryIndex
        extends CoCategoryIndex<DiaryCategory, DiaryCategoryMask> {

    public static final String SCHEMA = "diarycat";

    public DiaryCategoryIndex() {
        super(SCHEMA);
    }

}
