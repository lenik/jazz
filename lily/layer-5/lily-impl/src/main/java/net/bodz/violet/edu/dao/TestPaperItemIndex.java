package net.bodz.violet.edu.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.edu.TestPaperItem;

/**
* @label TestPaperItem
*/
@ObjectType(TestPaperItem.class)
public class TestPaperItemIndex
        extends CoIndex<TestPaperItem, TestPaperItemCriteriaBuilder> {

    public TestPaperItemIndex() {
    }

}
