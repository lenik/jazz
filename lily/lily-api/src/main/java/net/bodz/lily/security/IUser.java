package net.bodz.lily.security;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.rtx.IAttributed;

public interface IUser
        extends
            IAttributed,
            IJsonForm {

    Integer id();

    String getName();

    String getFullName();

    boolean isSuperUser();

    IUserSecret getSecret();

    IGroup getPrimaryGroup();

}
