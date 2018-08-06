package net.bodz.lily.template;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

@IdType(Long.class)
public abstract class DocRecord
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

}
