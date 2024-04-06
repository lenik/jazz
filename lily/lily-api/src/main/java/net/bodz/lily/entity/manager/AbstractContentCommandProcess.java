package net.bodz.lily.entity.manager;

public abstract class AbstractContentCommandProcess
        extends AbstractEntityCommandProcess {

    protected ResolvedEntity resolvedEntity;

    public AbstractContentCommandProcess(IEntityCommandType type, IEntityCommandContext context,
            ResolvedEntity resolvedEntity) {
        super(type, context);
        this.resolvedEntity = resolvedEntity;
    }

    @Override
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

}
