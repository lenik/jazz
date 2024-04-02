package net.bodz.lily.schema.meta;

import javax.persistence.Table;

/**
 * @label Priority
 * @label.zh.cn 优先级
 */
@Table(schema = PriorityDef.SCHEMA_NAME, name = PriorityDef.TABLE_NAME)
public class PriorityDef
        extends _PriorityDef_stuff<PriorityDef> {

    private static final long serialVersionUID = 1L;

}
