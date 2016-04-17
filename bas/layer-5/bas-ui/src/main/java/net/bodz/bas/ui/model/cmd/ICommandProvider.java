package net.bodz.bas.ui.model.cmd;

import java.util.List;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface ICommandProvider {

    Class<?> getTargetClass();

    List<ICommand> getCommands(Object object);

}
