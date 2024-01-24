package net.bodz.lily.schema.account.dao;

import java.util.List;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;

public interface GroupMapper
        extends
            IEntityMapper<Group> {

    List<Group> forUserId(int userId);

    default List<Group> forUser(User user) {
        if (user == null)
            throw new NullPointerException("user");
        Integer id = user.getId();
        if (id == null)
            throw new NullPointerException("user.id");
        return forUserId(id);
    }

}
