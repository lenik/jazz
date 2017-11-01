package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

/**
 * @see net.bodz.violet.plan.impl.DiaryMask
 * @see net.bodz.violet.plan.impl.DiaryMapper
 * @see net.bodz.violet.plan.impl.DiaryIndex
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryMapper.xml
 */
@IdType(Long.class)
public class Diary
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public Diary() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diary: ...");
        return sb.toString();
    }

}
