package net.bodz.lily.model.contact.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.lily.model.contact.Organization;

public interface OrganizationMapper
        extends IMapperTemplate<Organization, OrganizationMask> {

    Organization selectByTaxId(String taxId);

}
