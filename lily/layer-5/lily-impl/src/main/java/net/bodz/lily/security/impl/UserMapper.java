package net.bodz.lily.security.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserOtherIdType;

/**
 * @mapper.xml UserMapper.xml
 */
public interface UserMapper
        extends IMapperTemplate<User, UserMask> {

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

}
