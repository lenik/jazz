package net.bodz.lily.schema.meta;

import javax.persistence.Table;

/**
 * @label Phase
 * @label.zh.cn 阶段
 */
@Table(schema = PhaseDef.SCHEMA_NAME, name = PhaseDef.TABLE_NAME)
public class PhaseDef
        extends _PhaseDef_stuff {

    private static final long serialVersionUID = 1L;

}
