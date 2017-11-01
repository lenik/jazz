package net.bodz.violet.plan;

import net.bodz.lily.model.base.CoCode;

/**
 * @see net.bodz.violet.plan.impl.DiaryPhaseMask
 * @see net.bodz.violet.plan.impl.DiaryPhaseMapper
 * @see net.bodz.violet.plan.impl.DiaryPhase_htm
 * @see net.bodz.violet.plan.impl.DiaryPhaseIndex
 * @see net.bodz.violet.plan.impl.DiaryPhaseIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryPhaseMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryPhase_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryPhase_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryPhaseIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryPhaseIndex_htm.js
 */
public class DiaryPhase
        extends CoCode<DiaryPhase> {

    private static final long serialVersionUID = 1L;

    public DiaryPhase() {
        super();
    }

    public DiaryPhase(DiaryPhase parent) {
        super(parent);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryPhase: ...");
        return sb.toString();
    }

}
