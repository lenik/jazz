package net.bodz.lily.entity.manager;

import java.util.ArrayList;
import java.util.List;

import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public abstract class AbstractEntityCommandProvider
        implements
            IEntityCommandProvider {

    List<IEntityCommandType> commandTypes;

    public AbstractEntityCommandProvider() {
        commandTypes = new ArrayList<>();
    }

    protected void addCommand(IEntityCommandType type) {
        commandTypes.add(type);
    }

    @Override
    public List<IEntityCommandType> getCommands(Class<?> entityClass) {
        IEntityTypeInfo entityTypeInfo = EntityTypes.getTypeInfo(entityClass);
        List<IEntityCommandType> selection = new ArrayList<>(commandTypes.size());
        for (IEntityCommandType commandType : commandTypes) {
            if (!commandType.isEnabled(entityTypeInfo))
                continue;

            selection.add(commandType);
        }
        return selection;
    }

}
