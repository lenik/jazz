package net.bodz.lily.model.base.security;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import net.bodz.bas.db.batis.IMapper;

public interface UserMapper
        extends IMapper {

    List<User> all();

    User select(int id);

    int insert(User user);

    void update(User user);

    @Delete("delete from \"user\" where id=#{id}")
    boolean delete(int id);

}
