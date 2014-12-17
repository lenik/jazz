package net.bodz.lily.model.mx.base;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateRange;
import net.bodz.bas.t.range.IntRange;

import net.bodz.lily.model.base.CoEntityCriteria;
import net.bodz.lily.model.sea.QVariantMap;

public class CoMessageCriteria
        extends CoEntityCriteria {

    Integer opId;
    DateRange mailDateRange;

    IntRange voteCountRange;
    IntRange likerCountRange;
    IntRange readCountRange;

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public DateRange getMailDateRange() {
        return mailDateRange;
    }

    public void setMailDateRange(DateRange mailDateRange) {
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

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        opId = map.getInt("op", opId);
        voteCountRange = map.getIntRange("votes", voteCountRange);
        likerCountRange = map.getIntRange("likes", likerCountRange);
        readCountRange = map.getIntRange("reads", readCountRange);
    }

}
