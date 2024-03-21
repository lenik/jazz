package net.bodz.lily.concrete;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.meta.TsTyped;

@FieldGroupVue
@IdType(Long.class)
@TsTyped
public abstract class BackrefRecord
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    // site
    // key
    // value

}
