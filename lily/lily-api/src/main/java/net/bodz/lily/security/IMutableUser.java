package net.bodz.lily.security;

public interface IMutableUser
        extends
            IUser {

    void id(Integer newId);

    void setUniqName(String uniqName);

    void setFullName(String fullName);

    void setSuperUser(boolean superUser);

    void setPrimaryGroup(IGroup primaryGroup);

}
