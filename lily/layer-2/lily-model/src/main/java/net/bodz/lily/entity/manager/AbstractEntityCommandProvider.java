package net.bodz.lily.entity.manager;

import java.util.ArrayList;
import java.util.List;

import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public abstract class AbstractEntityCommandProvider
        implements
            IEntityCommandProvider {

    List<IEntityCommandBuilder> commandBuilders;

    public AbstractEntityCommandProvider() {
        commandBuilders = new ArrayList<>();
    }

    protected void addCommand(IEntityCommandBuilder builder) {
        commandBuilders.add(builder);
    }

    @Override
    public List<IEntityCommand> getCommands(Class<?> entityClass) {
        IEntityTypeInfo typeInfo = EntityTypes.getTypeInfo(entityClass);
        List<IEntityCommand> selection = new ArrayList<>(commandBuilders.size());
        for (IEntityCommandBuilder b : commandBuilders) {
            b.entityType(typeInfo);
            if (!b.checkValid())
                continue;

            IEntityCommand command = b.build();
            if (!checkInclude(command, typeInfo))
                continue;

            selection.add(command);
        }
        return selection;
    }

    protected boolean checkInclude(IEntityCommand command, IEntityTypeInfo typeInfo) {
        return true;
    }

}
