package net.bodz.lily.schema.meta;

import javax.persistence.Table;

/**
 * @label Parameter
 * @label.zh.cn 参数
 */
@Table(schema = ParameterDef.SCHEMA_NAME, name = ParameterDef.TABLE_NAME)
public class ParameterDef
        extends _ParameterDef_stuff {

    private static final long serialVersionUID = 1L;

}
