package net.bodz.lily.entity.manager;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.type.IEntityTypeInfo;

@ForEntityType(IId.class)
public class ResolveCommand
        extends AbstractEntityCommand {

    JsonFormOptions jsonFormOptions;

    public ResolveCommand(IEntityTypeInfo typeInfo) {
        super(typeInfo);
    }

    @Override
    public String getPreferredName() {
        return null;
    }

    @Override
    public Object execute()
            throws Exception {
        JsonWrapper data = JsonWrapper.wrap(resolvedEntity, "data");
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
            super(ResolveCommand.class);
        }

        @Override
        public ResolveCommand build() {
            return new ResolveCommand(typeInfo);
        }
    }

}
