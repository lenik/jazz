package net.bodz.lily.model.mx.base;

import net.bodz.bas.t.set.IntRange;

import net.bodz.lily.model.base.CoEntityCriteria;

public class CoMessageCriteria
        extends CoEntityCriteria {

    IntRange voteCountRange;

    IntRange likerCountRange;

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

}
