package net.bodz.lily.security;

import java.util.List;

import javax.persistence.Table;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.lily.entity.IdType;

@Table(name = "role")
@IdType(Integer.class)
public class Role
        extends CoPrincipal {

    private static final long serialVersionUID = 1L;

    public static final int ID_Manage = 0;

    public static final int RANK_ROOT = 0;
    public static final int RANK_NORMAL = 30;
    public static final int RANK_GUEST = 90;

    private int rank = RANK_NORMAL;
    private List<User> users;

    public Role() {
    }

    public Role(int id) {
        setId(id);
    }

    public Role(int id, String name, String fullName) {
        setId(id);
        setName(name);
        setFullName(fullName);
    }

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

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);
        rank = o.getInt("rank", rank);
    }

}
