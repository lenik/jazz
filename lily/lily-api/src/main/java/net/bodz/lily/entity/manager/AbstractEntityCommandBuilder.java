package net.bodz.lily.entity.manager;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public abstract class AbstractEntityCommandBuilder<This extends AbstractEntityCommandBuilder<This>>
        implements
            IEntityCommandBuilder {

    static final Logger logger = LoggerFactory.getLogger(AbstractEntityCommandBuilder.class);

    protected final Class<This> thisClass;
    protected final Class<?> commandClass;

    protected IEntityTypeInfo typeInfo;

    @SuppressWarnings("unchecked")
    public AbstractEntityCommandBuilder(Class<?> commandClass) {
        thisClass = ((Class<This>) getClass());
        this.commandClass = commandClass;
    }

    @Override
    public This entityType(Class<?> entityClass) {
        IEntityTypeInfo typeInfo = EntityTypes.getTypeInfo(entityClass);
        return entityType(typeInfo);

    }

    @Override
    public This entityType(IEntityTypeInfo typeInfo) {
        this.typeInfo = typeInfo;
        return thisClass.cast(this);
    }

}
