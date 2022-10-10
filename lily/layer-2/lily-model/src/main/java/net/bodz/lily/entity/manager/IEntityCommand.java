package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public interface IEntityCommand
        extends
            IPriority,
            IDataContextAware,
            IPathDispatchable,
            IVarMapForm {

    String getPreferredName();

    /**
     * content command can be dispatched by: <code>/FooBar/(id)/cmd</code>
     *
     * instead of <code>/FooBar/cmd</code>
     */
    boolean isContentCommand();

    /**
     * Used for content command.
     */
    ResolvedEntity getResolvedEntity();

    void setResolvedEntity(ResolvedEntity entity);

    boolean checkValid(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException;

    Object execute()
            throws Exception;

    JsonResult getResult();

}
