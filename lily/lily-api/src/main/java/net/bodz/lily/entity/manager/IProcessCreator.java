package net.bodz.lily.entity.manager;

@FunctionalInterface
public interface IProcessCreator {

    IEntityCommandProcess createProcess(IEntityCommandType type, IEntityCommandContext context,
            ResolvedEntity resolvedEntity);

}
