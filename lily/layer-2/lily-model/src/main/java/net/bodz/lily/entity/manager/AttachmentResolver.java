package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@ForEntityType(IId.class)
public class AttachmentResolver
        extends AbstractEntityCommand {

    JsonFormOptions jsonFormOptions;

    public AttachmentResolver(IEntityTypeInfo typeInfo) {
        super(typeInfo);
        ITokenQueue tq;
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

        jsonFormOptions = new JsonFormOptions();
        jsonFormOptions.readObject(map);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractEntityCommandBuilder<Builder> {

        public Builder() {
            super(AttachmentResolver.class);
        }

        @Override
        public AttachmentResolver build() {
            return new AttachmentResolver(typeInfo);
        }
    }

}
