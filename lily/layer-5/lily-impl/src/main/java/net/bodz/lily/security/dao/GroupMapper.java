package net.bodz.lily.security.dao;

import java.util.List;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;

public interface GroupMapper
        extends
            IEntityMapper<Group, GroupMask> {

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
