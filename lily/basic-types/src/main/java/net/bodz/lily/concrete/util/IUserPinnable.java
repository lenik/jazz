package net.bodz.lily.concrete.util;

import java.util.Collection;


/**
 * @label Likable
 * @label.zh 收藏
 */
public interface IUserPinnable {

    int getPinCount();

    Collection<UserPinRecord> getPinRecords();

}
