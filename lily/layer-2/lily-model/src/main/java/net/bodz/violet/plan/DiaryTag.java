package net.bodz.violet.plan;

import net.bodz.lily.model.base.CoCode;

/**
 * @see net.bodz.violet.plan.impl.DiaryTagMask
 * @see net.bodz.violet.plan.impl.DiaryTagMapper
 * @see net.bodz.violet.plan.impl.DiaryTagIndex
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryTagMapper.xml
 */
public class DiaryTag
        extends CoCode<DiaryTag> {

    private static final long serialVersionUID = 1L;

    public DiaryTag() {
        super();
    }

    public DiaryTag(DiaryTag parent) {
        super(parent);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryTag: ...");
        return sb.toString();
    }

}
