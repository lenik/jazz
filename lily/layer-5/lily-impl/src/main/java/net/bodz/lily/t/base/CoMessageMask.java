package net.bodz.lily.t.base;

import java.util.Collection;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntRange;
import net.bodz.lily.model.base.CoMomentIntervalMask;

public abstract class CoMessageMask
        extends CoMomentIntervalMask {

    public Integer formId;

    public Integer opId;
    public Integer categoryId;
    public Integer phaseId;
    public Integer tagId;
    public Collection<Integer> tagIds;

    public DateTimeRange mailDateRange;

    public IntRange voteCountRange;
    public IntRange likerCountRange;
    public IntRange readCountRange;

    public boolean noForm;
    public boolean noOp;
    public boolean noCategory;
    public boolean noPhase;
    public boolean noTag;
    public boolean noMailDate;

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Collection<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Collection<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public DateTimeRange getMailDateRange() {
        return mailDateRange;
    }

    public void setMailDateRange(DateTimeRange mailDateRange) {
        this.mailDateRange = mailDateRange;
    }

    /**
     * Vote Count Range
     */
    public IntRange getVoteCountRange() {
        return voteCountRange;
    }

    public void setVoteCountRange(IntRange voteCountRange) {
        this.voteCountRange = voteCountRange;
    }

    /**
     * Liker Count Range
     */
    public IntRange getLikerCountRange() {
        return likerCountRange;
    }

    public void setLikerCountRange(IntRange likerCountRange) {
        this.likerCountRange = likerCountRange;
    }

    public IntRange getReadCountRange() {
        return readCountRange;
    }

    public void setReadCountRange(IntRange readCountRange) {
        this.readCountRange = readCountRange;
    }

    public boolean isNoOp() {
        return noOp;
    }

    public void setNoOp(boolean noOp) {
        this.noOp = noOp;
    }

    public boolean isNoCategory() {
        return noCategory;
    }

    public void setNoCategory(boolean noCategory) {
        this.noCategory = noCategory;
    }

    public boolean isNoTag() {
        return noTag;
    }

    public void setNoTag(boolean noTag) {
        this.noTag = noTag;
    }

    public boolean isNoMailDate() {
        return noMailDate;
    }

    public void setNoMailDate(boolean noMailDate) {
        this.noMailDate = noMailDate;
    }

    public boolean isNoPhase() {
        return noPhase;
    }

    public void setNoPhase(boolean noPhase) {
        this.noPhase = noPhase;
    }

}
