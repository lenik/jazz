package net.bodz.lily.schema.contact;

import javax.persistence.Table;

@Table(schema = Person.SCHEMA_NAME, name = Person.TABLE_NAME)
public class Person
        extends _Person_stuff {

    private static final long serialVersionUID = 1L;

}
