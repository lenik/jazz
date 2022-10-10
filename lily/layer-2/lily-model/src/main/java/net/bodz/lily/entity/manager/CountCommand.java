package net.bodz.lily.entity.manager;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class CountCommand
        extends AbstractEntityCommand {

    IVarMapForm criteria;

    public CountCommand(IEntityTypeInfo typeInfo) {
        super(typeInfo);
    }

    @Override
    public Object execute()
            throws Exception {
        long totalCount = getGenericMapper().count(criteria);
        return totalCount;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
        try {
            criteria = (IVarMapForm) typeInfo.getCrtieriaClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalUsageException(e.getMessage(), e);
        }
        criteria.readObject(map);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractEntityCommandBuilder<Builder> {

        public Builder() {
            super(CountCommand.class);
        }

        @Override
        public CountCommand build() {
            return new CountCommand(typeInfo);
        }
    }

}
