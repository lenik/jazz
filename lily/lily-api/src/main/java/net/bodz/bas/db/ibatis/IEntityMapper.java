package net.bodz.bas.db.ibatis;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ws.ICommonDataWithIdOps;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public interface IEntityMapper<T>
        extends
            IGenericMapper<T>,
            ICommonDataWithIdOps<T> {

    T selectByCodeName(@Param("code") String codeName);

}
