package net.bodz.lily.schema.meta;

import javax.persistence.Table;

/**
 * @label Category
 * @label.zh.cn 类別
 */
@Table(schema = CategoryDef.SCHEMA_NAME, name = CategoryDef.TABLE_NAME)
public class CategoryDef
        extends _CategoryDef_stuff {

    private static final long serialVersionUID = 1L;

}
