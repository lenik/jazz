package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 区域标签
 */
@IdType(Integer.class)
@Table(name = "regiontag")
public class RegionTag
        extends CoNode<RegionTag, Integer> {

    private static final long serialVersionUID = 1L;

}
