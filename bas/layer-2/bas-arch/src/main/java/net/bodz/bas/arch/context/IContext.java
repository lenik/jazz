package net.bodz.bas.arch.context;

public interface IContext {

    String getContextName();

    IContext getParentContext();

    boolean isPenetrated();

}
