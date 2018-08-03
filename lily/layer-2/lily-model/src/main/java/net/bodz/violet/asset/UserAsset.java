package net.bodz.violet.asset;

import javax.persistence.Table;

import net.bodz.lily.security.User;

@Table(name = "asset_user")
public class UserAsset
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    User owner;

    public UserAsset() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
