package net.bodz.lily.util;

import java.util.List;

import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.lily.model.base.CoObjectMask;

public interface IRandomPicker {

    default //
    <entity_t, mask_t extends CoObjectMask, mapper_t extends IGenericMapper<entity_t, mask_t>> //
    entity_t pickAny(Class<mapper_t> mapperClass, String tableName) {
        List<entity_t> some = pickSome(mapperClass, tableName, 1);
        return some.isEmpty() ? null : some.get(0);
    }

    <entity_t, mask_t extends CoObjectMask, mapper_t extends IGenericMapper<entity_t, mask_t>> //
    List<entity_t> pickSome(Class<mapper_t> mapperClass, String tableName, int limit);

}
