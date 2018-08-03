package net.bodz.violet.asset;

import javax.persistence.Table;

import net.bodz.lily.contact.Organization;

@Table(name = "asset_org")
public class OrgAsset
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    Organization owner;

    public OrgAsset() {
    }

    public Organization getOwner() {
        return owner;
    }

    public void setOwner(Organization owner) {
        this.owner = owner;
    }

}
