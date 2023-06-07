package net.bodz.violet.edu;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.template.CoTag;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _CourseTag_stuff<this_t extends _CourseTag_stuff<this_t>>
        extends CoTag<this_t> {

    private static final long serialVersionUID = 1L;

    public void initNotNulls() {
    }

}
