package net.bodz.lily.security;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.typer.std.IAttributes;

public interface IUser
        extends
            IAttributes,
            IJsonForm {

    Integer id();

    String getName();

    String getFullName();

    boolean isSuperUser();

    IUserSecret getSecret();

    IGroup getPrimaryGroup();

}
