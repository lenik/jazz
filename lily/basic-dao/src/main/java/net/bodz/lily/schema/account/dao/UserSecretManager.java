package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.UserSecret;

public class UserSecretManager
        extends AbstractEntityManager<UserSecret, UserSecretMapper> {

    public UserSecretManager(DataContext dataContext) {
        super(dataContext, UserSecretMapper.class);
    }

}
