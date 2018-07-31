package net.bodz.lily.model.base.security.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.lily.model.base.security.User;

/**
 * @mapper.xml UserMapper.xml
 */
public interface UserMapper
        extends IMapperTemplate<User, UserMask> {

    List<User> selectByEmail(//
            @Param("email") String email);

    List<User> selectByMobile(//
            @Param("mobile") String mobile);

    List<User> findForLogin(//
            @Param("code") String name, //
            @Param("password") String password);

}
