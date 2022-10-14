package net.bodz.bas.program.model;

public interface IMutableOption
        extends
            IOption {

    void setGroup(IOptionGroup group);

    void addAlias(String alias);

    void removeAlias(String alias);

}
