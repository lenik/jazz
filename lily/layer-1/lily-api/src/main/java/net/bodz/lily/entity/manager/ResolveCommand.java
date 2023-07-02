package net.bodz.lily.entity.manager;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.site.json.JsonWrapper;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;

@ForEntityType(IId.class)
public class ResolveCommand
        extends AbstractEntityCommandType {

    public ResolveCommand() {
    }

    @Override
    public String getPreferredName() {
        return null;
    }

    @Override
    public IEntityCommandProcess createProcess(IEntityCommandContext context) {
        return new ResolveProcess(this, context);
    }

}

class ResolveProcess
        extends AbstractEntityCommandProcess<ResolveCommand> {

    JsonFormOptions jsonFormOptions;

    public ResolveProcess(ResolveCommand type, IEntityCommandContext context) {
        super(type, context);
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

}