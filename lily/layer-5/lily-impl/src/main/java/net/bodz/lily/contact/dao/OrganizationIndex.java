package net.bodz.lily.contact.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.model.base.CoIndex;

/**
* @label Organization
*/
@ObjectType(Organization.class)
public class OrganizationIndex
        extends CoIndex<Organization, OrganizationCriteriaBuilder> {

    public OrganizationIndex() {
    }

}
