package net.bodz.lily.security.login;

import net.bodz.lily.concrete.CoObject;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.type.AbstractEntityOpListener;
import net.bodz.lily.security.IGroup;
import net.bodz.lily.security.IUser;

@ForEntityType(CoObject.class)
public class SetOwnerProperties
        extends AbstractEntityOpListener<CoObject> {

    @Override
    public void onLoad(CoObject o) {
        LoginToken token = LoginToken.fromRequest();
        IUser user = token.getUser();
        IGroup group = user.getPrimaryGroup();
        o.setOwnerUser(user);
        o.setOwnerGroup(group);
    }

}
