package net.bodz.lily.entity.manager;

import net.bodz.bas.c.string.Strings;
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
    private final String preferredName;

    public AbstractEntityCommandType() {
        Priority aPriority = getClass().getAnnotation(Priority.class);
        if (aPriority != null)
            priority = aPriority.value();
        else
            priority = 0;

        String name = getClass().getSimpleName();
        if (name.endsWith("Command"))
            name = name.substring(0, name.length() - 7);
        name = Strings.lcfirst(name);
        this.preferredName = name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getPreferredName() {
        return preferredName;
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
                if (!iface.isAssignableFrom(entityClass))
                    return false;
        }

        ForMapperType aForMapperType = typeClass.getAnnotation(ForMapperType.class);
        if (aForMapperType != null) {
            Class<?> mapperClass = typeInfo.getMapperClass();
            if (mapperClass == null)
                return false;
            for (Class<?> iface : aForMapperType.value())
                if (!iface.isAssignableFrom(mapperClass))
                    return false;
        }

        ForEntityCriteriaType aForEntityCriteriaType = typeClass.getAnnotation(ForEntityCriteriaType.class);
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

    @Override
    public boolean checkPathValid(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return true;
    }

}
