package net.bodz.lily.security;

import java.util.List;

import javax.persistence.Table;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.lily.entity.IdType;

/**
 * <p lang="zh-cn">
 * 用户组可以用来方便地将系统用户分组归类。在使用过程中，可以按用户组来成批地设置用户的权限。
 *
 * 因此，在一开始就合理设置用户组会为今后的使用带来很大的便利。
 *
 * 企业中可以为部门、车间等设置用户组，也可以为岗位、职能等角色设置用户组。
 *
 * 一个用户组可以有多个用户，一个用户也可以属于多个用户组。
 *
 * @label Group (Role)
 * @label.zh 用户组（角色）
 *
 * @rel user: 管理用户帐户
 * @rel orgunit: 管理组织单元（部门、车间）
 * @rel org: 管理联系人（组织、企业）
 *
 * @see <a href="http://www.williamlong.info/archives/2937.html">个人密码安全策略</a>
 * @see <a href="http://wenku.baidu.com/view/e8638601eff9aef8941e065e.html">用户名大全</a>
 */
@Table(name = "group")
@IdType(Integer.class)
public class Group
        extends CoPrincipal {

    private static final long serialVersionUID = 1L;

    public static final int ID_Root = 0;
    public static final int ID_Administrators = 1;
    public static final int ID_NormalUsers = 2;
    public static final int ID_GuestUsers = 3;

    private User admin;
    private List<User> users;

    public Group() {
    }

    public Group(int id) {
        setId(id);
    }

    public Group(int id, String name, String fullName) {
        setId(id);
        setName(name);
        setFullName(fullName);
    }

    /**
     * 管理员
     */
    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
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
        admin = o.readInto("admin", admin, new User());
        // users
    }

}
