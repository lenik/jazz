package net.bodz.lily.contact.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.contact.PersonRole;
import net.bodz.lily.model.base.CoIndex;

/**
* @label PersonRole
*/
@ObjectType(PersonRole.class)
public class PersonRoleIndex
        extends CoIndex<PersonRole, PersonRoleMask> {

    public PersonRoleIndex() {
    }

}
