package net.bodz.violet.plan.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _DiaryPartyMask_stuff
        extends CoObjectMask {

    Long id;
    LongRange idRange = new LongRange();

    Long diaryId;
    LongRange diaryIdRange = new LongRange();

    Integer userId;
    IntegerRange userIdRange = new IntegerRange();

    Integer personId;
    IntegerRange personIdRange = new IntegerRange();

    Integer orgId;
    IntegerRange orgIdRange = new IntegerRange();

    Integer value;
    IntegerRange valueRange = new IntegerRange();

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public LongRange getIdRange() {
        return idRange;
    }

    public void setIdRange(LongRange range) {
        this.idRange = range;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long value) {
        this.diaryId = value;
    }

    public LongRange getDiaryIdRange() {
        return diaryIdRange;
    }

    public void setDiaryIdRange(LongRange range) {
        this.diaryIdRange = range;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    public IntegerRange getUserIdRange() {
        return userIdRange;
    }

    public void setUserIdRange(IntegerRange range) {
        this.userIdRange = range;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer value) {
        this.personId = value;
    }

    public IntegerRange getPersonIdRange() {
        return personIdRange;
    }

    public void setPersonIdRange(IntegerRange range) {
        this.personIdRange = range;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer value) {
        this.orgId = value;
    }

    public IntegerRange getOrgIdRange() {
        return orgIdRange;
    }

    public void setOrgIdRange(IntegerRange range) {
        this.orgIdRange = range;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public IntegerRange getValueRange() {
        return valueRange;
    }

    public void setValueRange(IntegerRange range) {
        this.valueRange = range;
    }

}
