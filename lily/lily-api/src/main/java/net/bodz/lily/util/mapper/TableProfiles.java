package net.bodz.lily.util.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.t.range.LongRange;
import net.bodz.bas.t.variant.MutableVariant;
import net.bodz.lily.criterion.CompareMode;
import net.bodz.lily.criterion.FieldCompare;
import net.bodz.lily.criterion.Junction;
import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;
import net.bodz.lily.util.IRandomPicker;

public class TableProfiles
        implements
            IRandomPicker {

    private DataContext context;

    public TableProfiles(DataContext context) {
        this.context = context;
    }

    @Override
    public <entity_t, mapper_t extends IGenericMapper<entity_t>> //
    entity_t pickAny(Class<mapper_t> mapperClass, String tableName) {
        List<entity_t> some = pickSome(mapperClass, tableName, 1);
        return some.isEmpty() ? null : some.get(0);
    }

    @Override
    public <entity_t, mapper_t extends IGenericMapper<entity_t>> //
    List<entity_t> pickSome(Class<mapper_t> mapperClass, String tableName, int limit) {
        mapper_t mapper = context.requireMapper(mapperClass);
        ProfileMapper prof = context.requireMapper(ProfileMapper.class);

        LongRange range = prof.findIdRange(tableName);
        if (range == null)
            return Collections.emptyList();
        range.end++; // exclusive.

        Random random = new Random();
        long w = range.end - range.start;
        long any = Math.abs(random.nextLong() % w);
        if (any < 0)
            any += w;
        any += range.start;
        MutableVariant vAny = new MutableVariant(any);

        Class<entity_t> enitityClass = TypeParam.infer1(mapperClass, IEntityMapper.class, 0);

        IEntityTypeInfo typeInfo = EntityTypes.getTypeInfo(enitityClass);
        String[] idColumns = typeInfo.getPrimaryKeyColumns();
        IProperty[] idProps = typeInfo.getPrimaryKeyProperties();

        // ICriteriaBuilder<?> criteriaBuilder = typeInfo.newCriteriaBuilder();
        Junction j = new Junction();

        for (int i = 0; i < idProps.length; i++) {
            String idColumn = idColumns[i];
            IProperty idProp = idProps[i];

            @SuppressWarnings("unchecked")
            Class<Object> idPropType = (Class<Object>) idProp.getPropertyClass();

            Object idFragment = vAny.convert(idPropType);
            j.add(new FieldCompare<Object>(idColumn, false, //
                    CompareMode.LESS_THAN, idPropType, idFragment));
        }

        SelectOptions opts = new SelectOptions();
        opts.order("id", false);
        opts.pageByIndex(0, limit);
        List<entity_t> list = mapper.filter(j, opts);
        return list;
    }

}
