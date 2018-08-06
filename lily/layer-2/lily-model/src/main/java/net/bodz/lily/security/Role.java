package net.bodz.lily.security;

import java.util.List;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;

@Table(name = "role")
@IdType(Integer.class)
public class Role
        extends CoPrincipal {

    private static final long serialVersionUID = 1L;

    public static final int RANK_ROOT = 0;
    public static final int RANK_NORMAL = 30;
    public static final int RANK_GUEST = 90;

    private int rank = RANK_NORMAL;
    private List<User> users;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * 用户
     */
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
