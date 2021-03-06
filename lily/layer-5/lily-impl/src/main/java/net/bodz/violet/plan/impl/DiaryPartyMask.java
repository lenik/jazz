package net.bodz.violet.plan.impl;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.violet.plan.DiaryParty
 */
public class DiaryPartyMask
        extends CoObjectMask {

    Long diaryId;
    Integer userId;
    Integer personId;
    Integer orgId;
    IntegerRange valueRange;

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public IntegerRange getValueRange() {
        return valueRange;
    }

    public void setValueRange(IntegerRange valueRange) {
        this.valueRange = valueRange;
    }

}
