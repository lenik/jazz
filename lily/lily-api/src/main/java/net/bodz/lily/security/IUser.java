package net.bodz.lily.security;

import net.bodz.bas.fmt.json.IJsonForm;

public interface IUser
        extends
            IJsonForm {

    Integer id();

    String getUniqName();

    String getFullName();

    boolean isSuperUser();

    IUserSecret getSecret();

    IGroup getPrimaryGroup();

}
