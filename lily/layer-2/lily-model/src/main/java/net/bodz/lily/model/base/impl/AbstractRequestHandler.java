package net.bodz.lily.model.base.impl;

import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.RequestHandlerException;

interface IRequestHandler {

    Object handle(IVariantMap<String> q)
            throws RequestHandlerException;

}

public abstract class AbstractRequestHandler
        implements IRequestHandler {

}
