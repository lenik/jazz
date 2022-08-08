package net.bodz.lily.util.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class TableProfiles {

    private DataContext context;

    public TableProfiles(DataContext context) {
        this.context = context;
    }

    public <entity_t, mask_t extends CoObjectMask, mapper_t extends IMapperTemplate<entity_t, mask_t>> //
    entity_t pickAny(Class<mapper_t> mapperClass, String tableName) {
        List<entity_t> some = pickSome(mapperClass, tableName, 1);
        return some.isEmpty() ? null : some.get(0);
    }

    public <entity_t, mask_t extends CoObjectMask, mapper_t extends IMapperTemplate<entity_t, mask_t>> //
    List<entity_t> pickSome(Class<mapper_t> mapperClass, String tableName, int limit) {
        Class<mask_t> maskClass = TypeParam.infer1(mapperClass, IMapperTemplate.class, 1);

        mapper_t mapper = context.requireMapper(mapperClass);
        ProfileMapper prof = context.requireMapper(ProfileMapper.class);

        LongRange range = prof.findIdRange(tableName);
        if (range == null)
            return Collections.emptyList();
        range.end++; // so exclusive.

        Random random = new Random();
        long w = range.end - range.start;
        long any = Math.abs(random.nextLong() % w);
        if (any < 0)
            any += w;
        any += range.start;

        mask_t m;
        try {
            m = maskClass.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
//        TODO m.getIdRange().setEnd(any + 1);

        SelectOptions opts = new SelectOptions();
        opts.order("id", false);
        opts.page(limit, 0);
        List<entity_t> list = mapper.filter(m, opts);
        return list;
    }

}
