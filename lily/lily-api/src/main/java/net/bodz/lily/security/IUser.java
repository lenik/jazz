package net.bodz.lily.security;

import net.bodz.bas.fmt.json.IJsonForm;

public interface IUser
        extends
            IJsonForm {

    Integer id();

    String getName();

    String getFullName();

    boolean isSuperUser();

    IUserSecret getSecret();

    IGroup getPrimaryGroup();

}
