package net.bodz.lily.entity.manager;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public class CountCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "count";
    public static final String[] NAMES = { NAME, "nRow" };

    public CountCommand() {
    }

    @Override
    public String getPreferredName() {
        return NAME;
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public CountProcess createProcess(IEntityCommandContext context) {
        return new CountProcess(this, context);
    }

}

class CountProcess
        extends AbstractEntityCommandProcess<CountCommand> {

    IVarMapForm criteria;

    public CountProcess(CountCommand type, IEntityCommandContext context) {
        super(type, context);
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

}
