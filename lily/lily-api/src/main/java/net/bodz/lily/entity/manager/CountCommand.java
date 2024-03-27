package net.bodz.lily.entity.manager;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.criterion.ICriterion;
import net.bodz.lily.criterion.Junction;

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
    public CountProcess createProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new CountProcess(this, context, resolvedEntity);
    }

}

class CountProcess
        extends AbstractEntityCommandProcess<CountCommand> {

    ICriterion criteria;

    public CountProcess(CountCommand type, IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        super(type, context, resolvedEntity);
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
        Junction j = new Junction();
        j.readObject(map);
        this.criteria = j;
    }

}
