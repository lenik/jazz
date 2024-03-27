package net.bodz.violet.schema.store;

import javax.persistence.Table;


/**
 * 区域分类
 */
@Table(schema = RegionCategory.SCHEMA_NAME, name = RegionCategory.TABLE_NAME)
public class RegionCategory
        extends _RegionCategory_stuff<RegionCategory> {

    private static final long serialVersionUID = 1L;

    public static final int ID_Abstracts = 1;
    public static final int ID_Templates = 2;

}
