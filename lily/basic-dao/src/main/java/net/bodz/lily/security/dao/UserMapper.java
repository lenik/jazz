package net.bodz.lily.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserOtherIdType;

/**
 * @mapper.xml UserMapper.xml
 */
public interface UserMapper
        extends
            IEntityMapper<User> {

    User selectByName(@Param("name") String name);

    List<User> selectByOtherId(//
            @Param("type") UserOtherIdType type, //
            @Param("oid") String oid);

    List<User> selectByPhoneNumber(//
            @Param("phone") String mobile);

    List<User> selectByEmail(//
            @Param("email") String email);

    List<User> findForLogin(//
            @Param("name") String name, //
            @Param("password") String password);

    List<User> forGroupId(int groupId);

    default List<User> forGroup(Group group) {
        if (group == null)
            throw new NullPointerException("group");
        Integer id = group.getId();
        if (id == null)
            throw new NullPointerException("group.id");
        return forGroupId(id);
    }

}
