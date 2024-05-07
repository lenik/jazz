package net.bodz.lily.schema.account;

import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.db.ibatis.IResultSetForm;
import net.bodz.bas.meta.bean.Internal;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Redundant;
import net.bodz.bas.typer.std.ITyperFamily;
import net.bodz.lily.concrete.util.ExtraAttributes;
import net.bodz.lily.entity.type.EntityTypes;
import net.bodz.lily.entity.type.IEntityTypeInfo;
import net.bodz.lily.security.IUser;

/**
 * <p lang="zh-cn">
 * 使用本系统的用户，在登录系统时需要输入密码。
 *
 * 和“联系人”不同，用户不具有联系信息。
 *
 * @label User (Account)
 * @label.zh 用户（帐户）
 *
 * @rel group: 管理用户组
 * @rel person: 管理联系人（自然人）
 * @rel org: 管理联系人（组织、企业）
 *
 * @see <a href="http://www.williamlong.info/archives/2937.html">个人密码安全策略</a>
 * @see <a href="http://wenku.baidu.com/view/e8638601eff9aef8941e065e.html">用户名大全</a>
 */
@Table(schema = User.SCHEMA_NAME, name = User.TABLE_NAME)
public class User
        extends _User_stuff
        implements
            IUser,
            IResultSetForm {

    private static final long serialVersionUID = 1L;

    public static final IEntityTypeInfo TYPE = EntityTypes.getTypeInfo(User.class);

    public static final int ID_Root = 0;
    public static final int ID_Admin = 1;
    public static final int ID_DefaultUser = 2;
    public static final int ID_Guest = 3;

    ExtraAttributes attributes = new ExtraAttributes(TYPE);

    private List<Group> groups = new ArrayList<>();
    private List<UserSecret> secrets = new ArrayList<>();
    private List<UserOtherId> oids = new ArrayList<>();

    private InetAddress registerIP;
    private UserRun runningState;

    public User() {
    }

    public User(int id, String name, String fullName) {
        id(id);
        setName(name);
        setFullName(fullName);
    }

    // @Internal
    public ExtraAttributes getAttributes() {
        return attributes;
    }

    @Internal
    @Override
    public Collection<String> getAttributeNames() {
        return attributes.getAttributeNames();
    }

    @Override
    public <T> T getAttribute(String name) {
        return attributes.getAttribute(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        return attributes.getAttribute(name, defaultValue);
    }

    @Override
    public ITyperFamily<?> getAttributeTypers(String attributeName) {
        return attributes.getAttributeTypers(attributeName);
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        if (groups == null)
            throw new NullPointerException("groups");
        this.groups = groups;
    }

    @Transient
    @Derived
    public Set<Integer> getGroupIds() {
        Set<Integer> gids = new HashSet<>();
        if (primaryGroup != null)
            gids.add(primaryGroup.id());
        if (groups != null)
            for (Group g : groups)
                gids.add(g.id());
        return gids;
    }

    public UserSecret makeSecret() {
        UserSecret secret;
        if (secrets.isEmpty()) {
            secret = new UserSecret();
            secret.setUser(this);
            secrets.add(secret);
        } else
            secret = secrets.get(0);
        return secret;
    }

    @Override
    public synchronized UserSecret getSecret() {
        if (secrets.isEmpty())
            return null;
        Object first = secrets.get(0);
        return (UserSecret) first;
    }

    public void setSecret(UserSecret secret) {
        secrets.clear();
        if (secret != null)
            secrets.add(secret);
    }

    public List<UserSecret> getSecrets() {
        return secrets;
    }

    public void setSecrets(List<UserSecret> secrets) {
        if (secrets == null)
            throw new NullPointerException("secrets");
        for (Object o : secrets) {
            if (! (o instanceof UserSecret))
                throw new IllegalArgumentException("Invalid type: " + o);
        }
        this.secrets = secrets;
    }

    public List<UserOtherId> getOtherIds() {
        return oids;
    }

    public void setIds(List<UserOtherId> otherIds) {
        if (otherIds == null)
            throw new NullPointerException("ids");
        this.oids = otherIds;
    }

    @Override
    @Redundant
    public boolean isSuperUser() {
        Integer id = id();
        if (id == null)
            return false;
        if (id == 0)
            return true;

        if (primaryGroup != null)
            if (primaryGroup.id() == 0)
                return true;

        if (groups != null)
            for (Group g : groups)
                if (g.id() == 0)
                    return true;

        return false;
    }

    /**
     * @label Register IP
     * @label.zh 注册IP
     */
    public InetAddress getRegisterIP() {
        return registerIP;
    }

    public void setRegisterIP(InetAddress registerIP) {
        this.registerIP = registerIP;
    }

    public UserRun getRunningState() {
        return runningState;
    }

    public void setRunningState(UserRun runningState) {
        this.runningState = runningState;
    }

    @Override
    public void readObject(ResultSet rs)
            throws SQLException {
        attributes.readObject(rs);
    }

}
