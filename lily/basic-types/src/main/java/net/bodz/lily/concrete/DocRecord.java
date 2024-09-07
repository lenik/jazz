package net.bodz.lily.concrete;

import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;

@FieldGroupVue
@IdType(Long.class)
@TsTyped
public abstract class DocRecord
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

}
