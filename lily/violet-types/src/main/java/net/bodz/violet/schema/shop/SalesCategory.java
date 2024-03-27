package net.bodz.violet.schema.shop;

import javax.persistence.Table;

@Table(schema = SalesCategory.SCHEMA_NAME, name = SalesCategory.TABLE_NAME)
public class SalesCategory
        extends _SalesCategory_stuff<SalesCategory> {

    private static final long serialVersionUID = 1L;

}
