package net.bodz.lily.model.mx.base;

import java.util.Collection;

/**
 * @label Likable
 * @label.zh 收藏
 */
public interface ILikable {

    int getLikerCount();

    Collection<Liker> getLikers();

}
