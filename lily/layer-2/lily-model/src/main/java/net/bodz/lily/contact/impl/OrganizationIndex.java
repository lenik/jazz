package net.bodz.lily.contact.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.contact.Organization;

/**
 * @label Organization
 */
@ObjectType(Organization.class)
public class OrganizationIndex
        extends PartyIndex<Organization, OrganizationMask> {

    public static final String SCHEMA = "org";

    public OrganizationIndex() {
        super(SCHEMA);
    }

}
