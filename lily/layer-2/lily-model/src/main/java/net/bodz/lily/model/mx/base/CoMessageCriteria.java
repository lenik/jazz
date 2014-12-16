package net.bodz.lily.model.mx.base;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.set.IntRange;

import net.bodz.lily.model.base.CoEntityCriteria;
import net.bodz.lily.model.sea.QVariantMap;

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

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        voteCountRange = map.getIntRange("votes", voteCountRange);
        likerCountRange = map.getIntRange("likes", likerCountRange);
    }

}
