package net.bodz.lily.api;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCode;

@Table(name = "appacntcat")
@IdType(Integer.class)
public class AppAccountType
        extends CoCode<AppAccountType> {

    private static final long serialVersionUID = 1L;

    public AppAccountType() {
    }

}
