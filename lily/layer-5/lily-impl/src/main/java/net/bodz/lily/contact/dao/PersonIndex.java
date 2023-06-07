package net.bodz.lily.contact.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.contact.Person;
import net.bodz.lily.model.base.CoIndex;

/**
* @label Person
*/
@ObjectType(Person.class)
public class PersonIndex
        extends CoIndex<Person, PersonMask> {

    public PersonIndex() {
    }

}