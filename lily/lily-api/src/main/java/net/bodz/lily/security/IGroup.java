package net.bodz.lily.security;

import net.bodz.bas.fmt.json.IJsonForm;

public interface IGroup
        extends
            IJsonForm {

    Integer id();

    String getUniqName();

    String getFullName();

}
