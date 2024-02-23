package net.bodz.lily.schema.meta;

import javax.persistence.Table;

/**
 * @label Tag
 * @label.zh.cn 标签
 */
@Table(schema = TagDef.SCHEMA_NAME, name = TagDef.TABLE_NAME)
public class TagDef
        extends _TagDef_stuff {

    private static final long serialVersionUID = 1L;

}
