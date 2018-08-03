package net.bodz.violet.asset;

import javax.persistence.Table;

import net.bodz.lily.contact.OrgUnit;

@Table(name = "asset_ou")
public class OrgUnitAsset
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    OrgUnit owner;

    public OrgUnit getOwner() {
        return owner;
    }

    public void setOwner(OrgUnit owner) {
        this.owner = owner;
    }

}
