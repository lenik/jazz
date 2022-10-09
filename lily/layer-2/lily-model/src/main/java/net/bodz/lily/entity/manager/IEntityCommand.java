package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.variant.IVarMapForm;

public interface IEntityCommand
        extends
            IPriority,
            IDataContextAware,
            IVarMapForm {

    String getPreferredName();

    /**
     * content command can be dispatched by: <code>/FooBar/(id)/cmd</code>
     *
     * instead of <code>/FooBar/cmd</code>
     */
    boolean isContentCommand();

    Object execute(IEntityCommandContext executeContext)
            throws Exception;

}
