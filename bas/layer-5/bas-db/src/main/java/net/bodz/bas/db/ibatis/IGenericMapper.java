package net.bodz.bas.db.ibatis;

import org.apache.ibatis.annotations.Flush;

import net.bodz.bas.db.ws.ICommonDataOps;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public interface IGenericMapper<T>
        extends
            IMapper,
            ICommonDataOps<T> {

    @Commit
    @Flush
    void flush();

}
