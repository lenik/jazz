package net.bodz.lily.contact.dao;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.contact.Organization;

public interface OrganizationMapper
        extends
            IEntityMapper<Organization, OrganizationCriteriaBuilder> {

    Organization selectByTaxId(String taxId);

}
