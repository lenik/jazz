package net.bodz.violet.issue;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.template.CoCategory;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _IssueCategory_stuff<this_t extends _IssueCategory_stuff<this_t>>
        extends CoCategory<this_t, Integer> {

    private static final long serialVersionUID = 1L;

    public void initNotNulls() {
    }

}