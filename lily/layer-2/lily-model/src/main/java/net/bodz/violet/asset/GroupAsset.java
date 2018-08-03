package net.bodz.violet.asset;

import javax.persistence.Table;

import net.bodz.lily.security.Group;

@Table(name = "asset_group")
public class GroupAsset
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    Group owner;

    public GroupAsset() {
    }

    public Group getOwner() {
        return owner;
    }

    public void setOwner(Group owner) {
        this.owner = owner;
    }

}
