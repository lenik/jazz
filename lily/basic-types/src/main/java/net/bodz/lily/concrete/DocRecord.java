package net.bodz.lily.concrete;

import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class DocRecord
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

}
