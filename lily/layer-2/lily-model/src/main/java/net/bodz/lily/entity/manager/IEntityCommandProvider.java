package net.bodz.lily.entity.manager;

import java.util.List;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IEntityCommandProvider {

    List<IEntityCommand> getCommands(Class<?> entityClass);

}
