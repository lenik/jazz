package net.bodz.bas.rtx;

import java.util.Collection;
import java.util.Set;

import net.bodz.bas.meta.bean.JsonRedundant;

public interface IAttributes
        extends
            IAttributed {

    IMutableAttributes NULL = NullAttributes.INSTANCE;

    /**
     * @return Never <code>null</code>, returns empty {@link Collection} if no public annotation available.
     */
    @JsonRedundant
    Set<String> getAttributeNames();

    boolean isAttributePresent(String name);

}
