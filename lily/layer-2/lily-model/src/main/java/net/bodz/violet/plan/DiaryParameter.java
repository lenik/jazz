package net.bodz.violet.plan;

import net.bodz.lily.model.base.CoCode;

/**
 * @see net.bodz.violet.plan.impl.DiaryParameterMask
 * @see net.bodz.violet.plan.impl.DiaryParameterMapper
 * @see net.bodz.violet.plan.impl.DiaryParameter_htm
 * @see net.bodz.violet.plan.impl.DiaryParameterIndex
 * @see net.bodz.violet.plan.impl.DiaryParameterIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryParameterMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryParameter_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryParameter_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryParameterIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryParameterIndex_htm.js
 */
public class DiaryParameter
        extends CoCode<DiaryParameter> {

    private static final long serialVersionUID = 1L;

    public DiaryParameter() {
        super();
    }

    public DiaryParameter(DiaryParameter parent) {
        super(parent);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryParameter: ...");
        return sb.toString();
    }

}
