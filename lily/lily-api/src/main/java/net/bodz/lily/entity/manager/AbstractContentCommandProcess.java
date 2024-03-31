package net.bodz.lily.entity.manager;

public abstract class AbstractContentCommandProcess<type_t extends IEntityCommandType>
        extends AbstractEntityCommandProcess<type_t> {

    protected ResolvedEntity resolvedEntity;

    public AbstractContentCommandProcess(type_t type, IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        super(type, context);
        this.resolvedEntity = resolvedEntity;
    }

    @Override
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

}
