package net.bodz.lily.entity.manager;

@FunctionalInterface
public interface IEntityCommandProcessFactory {

    IEntityCommandProcess createProcess(IEntityCommandType type, EntityCommandContext context);

}
