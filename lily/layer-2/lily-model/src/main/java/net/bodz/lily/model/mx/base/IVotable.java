package net.bodz.lily.model.mx.base;

import java.util.Collection;

/**
 * @label Votable
 * @label.zh 投票
 */
public interface IVotable {

    int getVoteCount();

    Collection<VoteRecord> getVoteRecords();

}
