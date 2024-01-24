package net.bodz.violet.schema.store;

import javax.persistence.Table;


/**
 * 区域分类
 */
@Table(schema = "violet", name = "regioncat")
public class RegionCategory
        extends _RegionCategory_stuff<RegionCategory> {

    private static final long serialVersionUID = 1L;

    public static final int ID_Abstracts = 1;
    public static final int ID_Templates = 2;

}
