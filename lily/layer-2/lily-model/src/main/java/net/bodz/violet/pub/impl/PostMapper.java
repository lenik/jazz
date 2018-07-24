package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.violet.pub.Post;
import net.bodz.violet.pub.impl.PostMask;

/**
 * @mapper.xml PostMapper.xml
 */
public interface PostMapper
        extends IMapperTemplate<Post, PostMask> {

}
