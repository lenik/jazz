package net.bodz.lily.schema.contact.dao;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.schema.contact.Organization;

public interface OrganizationMapper
        extends
            IEntityMapper<Organization> {

    Organization selectByTaxId(String taxId);

}
