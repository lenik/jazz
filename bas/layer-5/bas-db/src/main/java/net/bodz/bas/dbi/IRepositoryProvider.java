package net.bodz.bas.dbi;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.rtx.IOptions;

@IndexedType
public interface IRepositoryProvider {

    IRepository<?, ?> getRepository(Class<?> clazz, IOptions options);

}
