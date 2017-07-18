package net.bodz.lily.model.mixin;

import java.util.Collection;

/**
 * @label Votable
 * @label.zh 投票
 */
public interface IUserVotable {

    int getVoteCount();

    Collection<UserVoteRecord> getVoteRecords();

}
