package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

/**
 * @see net.bodz.violet.plan.impl.DiaryReplyMask
 * @see net.bodz.violet.plan.impl.DiaryReplyMapper
 * @see net.bodz.violet.plan.impl.DiaryReplyIndex
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryReplyMapper.xml
*/
@IdType(Long.class)
public class DiaryReply
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;
    
    public DiaryReply() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryReply: ...");
        return sb.toString();
    }

}
