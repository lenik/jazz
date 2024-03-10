package net.bodz.lily.schema.vapp;

import javax.persistence.Table;

@Table(schema = VAppCategory.SCHEMA_NAME, name = VAppCategory.TABLE_NAME)
public class VAppCategory
        extends _VAppCategory_stuff<VAppCategory> {

    private static final long serialVersionUID = 1L;

}
