package net.bodz.bas.db.ibatis;

public abstract class AbstractIbatisConfigurer
        implements IIbatisConfigurer {

    @Override
    public int getPriority() {
        return PRIORITY_MEDIUM;
    }

}
