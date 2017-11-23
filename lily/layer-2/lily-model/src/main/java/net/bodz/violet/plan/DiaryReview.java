package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

/**
 * @see net.bodz.violet.plan.impl.DiaryReviewMask
 * @see net.bodz.violet.plan.impl.DiaryReviewMapper
 * @see net.bodz.violet.plan.impl.DiaryReviewIndex
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReviewMapper.xml
*/
@IdType(Long.class)
public class DiaryReview
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;
    
    public DiaryReview() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryReview: ...");
        return sb.toString();
    }

}
