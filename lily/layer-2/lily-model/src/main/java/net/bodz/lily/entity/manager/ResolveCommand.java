package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@ForEntityType(IId.class)
public class ResolveCommand
        extends AbstractEntityCommand {

    public ResolveCommand(IEntityTypeInfo typeInfo) {
        super(typeInfo);
    }

    @Override
    protected Object execute()
            throws Exception {
        Object id = context.getAttribute("id");
        if (id == null)
            throw new NullPointerException("id");

        IEntityMapper<Object, Object> mapper = getEntityMapper();
        Object obj = mapper.select(id);
        if (obj == null)
            return null;

        JsonWrapper data = JsonWrapper.wrap(obj, "data");
        data.setOptions(jsonFormOptions);
        return data;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractEntityCommandBuilder<Builder> {

        public Builder() {
            super(ResolveCommand.class);
        }

        @Override
        public ResolveCommand build() {
            return new ResolveCommand(typeInfo);
        }
    }

}
