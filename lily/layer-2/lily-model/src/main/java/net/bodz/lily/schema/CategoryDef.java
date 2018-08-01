package net.bodz.lily.schema;

import javax.persistence.Table;

/**
 * @label Category
 * @label.zh.cn 类別
 */
@Table(name = "cat")
public class CategoryDef
        extends AbstractDefinition<CategoryDef> {

    private static final long serialVersionUID = 1L;

}
