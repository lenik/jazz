package net.bodz.lily.schema.meta;

import javax.persistence.Table;

@Table(schema = ParameterValue.SCHEMA_NAME, name = ParameterValue.TABLE_NAME)
public class ParameterValue
        extends _ParameterValue_stuff<ParameterValue> {

    private static final long serialVersionUID = 1L;

}
