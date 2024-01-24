package net.bodz.lily.dao;

import net.bodz.bas.db.ws.ICommonDataOps;
import net.bodz.bas.db.ws.ICommonDataWithIdOps;

public interface IEntityManager<T>
        extends
            ICommonDataOps<T>,
            ICommonDataWithIdOps<T> {

}
