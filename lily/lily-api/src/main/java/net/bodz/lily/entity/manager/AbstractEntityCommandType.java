package net.bodz.lily.entity.manager;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public abstract class AbstractEntityCommandType
        implements
            IEntityCommandType {

    static final Logger logger = LoggerFactory.getLogger(AbstractEntityCommandType.class);

    private final int priority;
    private final String id;

    public AbstractEntityCommandType(String id) {
        Priority aPriority = getClass().getAnnotation(Priority.class);
        if (aPriority != null)
            priority = aPriority.value();
        else
            priority = 0;
        this.id = id;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getUniqueId() {
        return id;
    }

    @Override
    public boolean isContentCommand() {
        return false;
    }

    @Override
    public boolean isEnabled(IEntityTypeInfo typeInfo) {
        Class<? extends IEntityCommandType> typeClass = getClass();

        ForEntityType aForEntityType = typeClass.getAnnotation(ForEntityType.class);
        if (aForEntityType != null) {
            Class<?> entityClass = typeInfo.getEntityClass();
            if (entityClass == null)
                return false;
            for (Class<?> iface : aForEntityType.value())
                if (! iface.isAssignableFrom(entityClass))
                    return false;
        }

        ForMapperType aForMapperType = typeClass.getAnnotation(ForMapperType.class);
        if (aForMapperType != null) {
            Class<?> mapperClass = typeInfo.getMapperClass();
            if (mapperClass == null)
                return false;
            for (Class<?> iface : aForMapperType.value())
                if (! iface.isAssignableFrom(mapperClass))
                    return false;
        }

        return true;
    }

    @Override
    public boolean checkPathValid(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return true;
    }

}
