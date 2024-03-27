package net.bodz.violet.schema.tran;

import javax.persistence.Table;

@Table(schema = TransportCategory.SCHEMA_NAME, name = TransportCategory.TABLE_NAME)
public class TransportCategory
        extends _TransportCategory_stuff<TransportCategory> {

    private static final long serialVersionUID = 1L;

}
