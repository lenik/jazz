package net.bodz.lily.schema.contact;

import javax.persistence.Table;

@Table(schema = PartyCategory.SCHEMA_NAME, name = PartyCategory.TABLE_NAME)
public class PartyCategory
        extends _PartyCategory_stuff<PartyCategory> {

    private static final long serialVersionUID = 1L;

}
