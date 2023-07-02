package net.bodz.lily.entity.manager;

import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVarMapForm;

public interface IEntityCommandProcess
        extends
            IPathDispatchable,
            IVarMapForm {

    default void setQueryContext(IQueryable context) {
    }

    String[] getPathInfo();

    void setPathInfo(String[] names);

    /**
     * Used for content command.
     */
    ResolvedEntity getResolvedEntity();

    Object execute()
            throws Exception;

    JsonResult getResult();

}
