package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCategory;

/**
 * 区域分类
 */
@IdType(Integer.class)
@Table(name = "regioncat")
public class RegionCategory
        extends CoCategory<RegionCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public static final int ID_Abstracts = 1;
    public static final int ID_Templates = 2;

}
