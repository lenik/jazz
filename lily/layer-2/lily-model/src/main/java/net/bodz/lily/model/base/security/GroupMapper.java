package net.bodz.lily.model.base.security;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import net.bodz.bas.db.batis.IMapper;

public interface GroupMapper
        extends IMapper {

    List<Group> all();

    Group select(int id);

    int insert(Group group);

    void update(Group group);

    @Delete("delete from \"group\" where id=#{id}")
    boolean delete(int id);

}
