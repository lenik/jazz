package net.bodz.lily.entity.manager.cmd;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.criterion.ICriterion;
import net.bodz.lily.criterion.Junction;
import net.bodz.lily.entity.manager.AbstractEntityCommandProcess;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

public class CountCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_COUNT;
    public static final String[] NAMES = { "count", "nRow" };

    public CountCommand() {
        super(ID);
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public CountProcess defaultCreateProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new CountProcess(this, context);
    }

}

class CountProcess
        extends AbstractEntityCommandProcess {

    ICriterion criteria;

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
            throws ParseException {
        super.readObject(map);
        Junction j = new Junction();
        j.readObject(map);
        this.criteria = j;
    }

}
