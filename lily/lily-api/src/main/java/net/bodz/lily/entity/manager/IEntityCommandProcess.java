package net.bodz.lily.entity.manager;

import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.variant.IVarMapForm;

public interface IEntityCommandProcess
        extends
            IPathDispatchable,
            IVarMapForm {

    IEntityCommandType getCommandType();

    default void setQueryContext(IQueryable context) {
    }

    IPathFields getCommandPath();

    void setCommandPath(IPathFields path);

    IPathFields getContentPath();

    void setContentPath(IPathFields path);

    /**
     * Used for content command.
     */
    ResolvedEntity getResolvedEntity();

    Object execute()
            throws Exception;

    JsonResult getResult();

}
