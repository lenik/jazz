package net.bodz.lily.security;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public class UserOtherIdType
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public UserOtherIdType() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
