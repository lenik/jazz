package net.bodz.lily.model.base.security;

import java.beans.Transient;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

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
@IdType(Integer.class)
public class User
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_LOGIN_NAME = 30;
    public static final int N_FULL_NAME = 40;
    public static final int N_EMAIL = 30;
    public static final int N_PASSWORD = 40;

    private Group primaryGroup;
    private List<Group> groups;

    private String email;
    private boolean emailValidated;

    private static final Random RANDOM = new Random();
    private int salt = RANDOM.nextInt();
    private String password;

    private List<UserId> ids;

    private InetAddress registerIP;
    private long lastLoginTime;
    private InetAddress lastLoginIP; // =Inet4Address.getByAddress();

    /**
     * @label Login Name
     * @label.zh 用户名
     */
    @TextInput(maxLength = N_LOGIN_NAME)
    public final String getLoginName() {
        return getCodeName();
    }

    public final void setLoginName(String loginName) {
        if (loginName == null)
            throw new NullPointerException("loginName");
        setCodeName(loginName);
    }

    /**
     * @label Full Name
     * @label.zh 真实姓名
     */
    @TextInput(maxLength = N_FULL_NAME)
    public final String getFullName() {
        return getLabel();
    }

    public final void setFullName(String fullName) {
        setLabel(fullName);
    }

    /**
     * @label Primary Group
     * @label.zh 首要的组
     */
    public Group getPrimaryGroup() {
        return primaryGroup;
    }

    public void setPrimaryGroup(Group primaryGroup) {
        this.primaryGroup = primaryGroup;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Derived
    @Transient
    public Set<Integer> getGroupIds() {
        Set<Integer> gids = new HashSet<>();
        if (primaryGroup != null)
            gids.add(primaryGroup.getId());
        if (groups != null)
            for (Group g : groups)
                gids.add(g.getId());
        return gids;
    }

    /**
     * E-mail
     */
    @TextInput(maxLength = N_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @label Email-Validated
     * @label.zh 邮箱通过验证
     */
    public boolean isEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(boolean emailValidated) {
        this.emailValidated = emailValidated;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    @TextInput(maxLength = N_PASSWORD)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserId> getIds() {
        return ids;
    }

    public void setIds(List<UserId> ids) {
        this.ids = ids;
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

    /**
     * @label Last Login Time
     * @label.zh 上次登录时间
     */
    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @label Last Login IP
     * @label.zh 上次登录IP
     */
    public InetAddress getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(InetAddress lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public boolean isAdmin() {
        Integer id = getId();
        if (id == null)
            return false;
        if (id == 0)
            return true;

        if (primaryGroup != null)
            if (primaryGroup.isAdmin())
                return true;

        for (Group g : groups)
            if (g.isAdmin())
                return true;

        return false;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        // primaryGroup, groups;
        email = map.getString("email", email);
        emailValidated = map.getBoolean("emailValidated", emailValidated);
        salt = map.getInt("salt", salt);
        password = map.getString("password", password);
        // ids
        // registerIP;
        // lastLoginTime;
        // lastLoginIP;
    }

}
