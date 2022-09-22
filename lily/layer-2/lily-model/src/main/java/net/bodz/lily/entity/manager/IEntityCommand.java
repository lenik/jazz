package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.t.variant.IVarMapForm;

public interface IEntityCommand
        extends
            IDataContextAware,
            IVarMapForm {

    String getPreferredName();

    Object execute(IEntityCommandContext executeContext)
            throws Exception;

}
