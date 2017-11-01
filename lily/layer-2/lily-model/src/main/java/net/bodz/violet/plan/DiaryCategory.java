package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * @see net.bodz.violet.plan.impl.DiaryCategoryMask
 * @see net.bodz.violet.plan.impl.DiaryCategoryMapper
 * @see net.bodz.violet.plan.impl.DiaryCategoryIndex
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryCategoryMapper.xml
 */
@IdType(Integer.class)
public class DiaryCategory
        extends CoNode<DiaryCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public DiaryCategory() {
        super();
    }

    public DiaryCategory(DiaryCategory parent) {
        super(parent);
    }

}
