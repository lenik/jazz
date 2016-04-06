package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.scope.id.IScopeDescriptor;

public interface IScopeTeller {

    /**
     * The same as {@link #tell(IScopeDescriptor)} with fallback set to <code>null</code>.
     */
    IScopeDescriptor tell();

}
