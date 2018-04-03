package net.bodz.lily.model.contact.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.contact.Person;

/**
 * @label Person
 */
@ObjectType(Person.class)
public class PersonIndex
        extends PartyIndex<Person, PersonMask> {

    public static final String SCHEMA = "person";

    public PersonIndex() {
        super(SCHEMA);
    }

}
