package net.bodz.lily.util.mapper;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IFnMapper;
import net.bodz.bas.t.range.LongRange;

public interface ProfileMapper
        extends IFnMapper {

    LongRange findIdRange(@Param("table") String table);

}
