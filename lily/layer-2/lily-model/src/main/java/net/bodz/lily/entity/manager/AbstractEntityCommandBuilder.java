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

    @Override
    public boolean checkValid() {
        if (typeInfo == null)
            throw new NullPointerException("typeInfo");

        ForEntityType aForEntityType = commandClass.getAnnotation(ForEntityType.class);
        if (aForEntityType != null) {
            Class<?> entityClass = typeInfo.getEntityClass();
            if (entityClass == null)
                return false;
            for (Class<?> iface : aForEntityType.value())
                if (!iface.isAssignableFrom(entityClass))
                    return false;
        }

        ForMapperType aForMapperType = commandClass.getAnnotation(ForMapperType.class);
        if (aForMapperType != null) {
            Class<?> mapperClass = typeInfo.getMapperClass();
            if (mapperClass == null)
                return false;
            for (Class<?> iface : aForMapperType.value())
                if (!iface.isAssignableFrom(mapperClass))
                    return false;
        }

        ForEntityCriteriaType aForEntityCriteriaType = commandClass.getAnnotation(ForEntityCriteriaType.class);
        if (aForEntityCriteriaType != null) {
            Class<?> crtieriaClass = typeInfo.getCrtieriaClass();
            if (crtieriaClass == null) {
                logger.warn("skipped: " + typeInfo.getEntityClass());
                return false;
            }
            for (Class<?> iface : aForEntityCriteriaType.value())
                if (!iface.isAssignableFrom(crtieriaClass))
                    return false;
        }

        return true;
    }

}
