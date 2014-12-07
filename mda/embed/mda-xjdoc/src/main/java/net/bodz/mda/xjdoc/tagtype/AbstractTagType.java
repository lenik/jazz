package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.c.type.AggregationEnum;
import net.bodz.bas.c.type.TypeKind;

public abstract class AbstractTagType
        implements ITagType {

    private final AggregationEnum aggregationEnum;

    public AbstractTagType() {
        Class<?> valueType = getValueType();
        aggregationEnum = TypeKind.getAggregationEnum(valueType);
    }

    @Override
    public AggregationEnum getAggregationEnum() {
        return aggregationEnum;
    }

}
