package net.bodz.lily.contact.impl;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.contact.Organization;

public interface OrganizationMapper
        extends IEntityMapper<Organization, OrganizationMask> {

    Organization selectByTaxId(String taxId);

}
