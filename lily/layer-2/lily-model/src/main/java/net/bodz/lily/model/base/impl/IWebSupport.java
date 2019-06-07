package net.bodz.lily.model.base.impl;

import java.io.IOException;

import org.apache.ibatis.exceptions.PersistenceException;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.IHtmlViewContext;

public interface IWebSupport {

    Object persist(IHtmlViewContext ctx, IHtmlOut out)
            throws PersistenceException, IOException;

}
