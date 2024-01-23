package net.bodz.lily.t.struct;

import java.util.Collection;


/**
 * @label Votable
 * @label.zh 投票
 */
public interface IUserVotable {

    int getVoteCount();

    Collection<UserVoteRecord> getVoteRecords();

}
