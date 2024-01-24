package net.bodz.lily.security;

public interface IMutableGroup
        extends
            IGroup {

    void id(Integer newId);

    void setUniqName(String uniqName);

    void setFullName(String fullName);

}
