package net.bodz.bas.db.ibatis;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.model.base.CoObjectMask;

@ExcludedFromIndex
public interface IMapperTemplate<T extends CoObject, M extends CoObjectMask>
        extends
            IGenericMapper<T, M> {

    T selectByCodeName(@Param("code") String codeName);

}
