package net.bodz.lily.security;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * User Category
 *
 * @label User Category
 * @label.zh 用户类型
 */
@Table(name = "usercat")
@IdType(Integer.class)
public class UserCategory
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int ID_DEFAULT = 0;

    public UserCategory() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
