package net.bodz.lily.model.base.security;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.bodz.lily.model.base.CoEntity;

/**
 * <p lang="zh-cn">
 * 使用本系统的用户，在登录系统时需要输入密码。和“联系人”不同，用户不具有联系信息。
 * 
 * @label User (Account)
 * @label.zh.cn 用户（帐户）
 * 
 * @rel group: 管理用户组
 * @rel person: 管理联系人（自然人）
 * @rel org: 管理联系人（组织、企业）
 * 
 * @see <a href="http://www.williamlong.info/archives/2937.html">个人密码安全策略</a>
 * @see <a href="http://wenku.baidu.com/view/e8638601eff9aef8941e065e.html">用户名大全</a>
 */
public class User
        extends CoEntity {

    private static final long serialVersionUID = 1L;

    public static final int N_LOGIN_NAME = 30;
    public static final int N_FULL_NAME = 40;
    public static final int N_EMAIL = 30;
    public static final int N_PASSWORD = 40;

    private int id;
    private Group primaryGroup;
    private final Set<Group> groups = new HashSet<Group>();

    private String email;
    private boolean emailValidated;

    private static final Random RANDOM = new Random();
    private int salt = RANDOM.nextInt();
    private String password;

    private long lastLoginTime;
    private InetAddress lastLoginIP; // =Inet4Address.getByAddress();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public final String getLoginName() {
        return getCodeName();
    }

    public final void setLoginName(String loginName) {
        if (loginName == null)
            throw new NullPointerException("loginName");
        setCodeName(loginName);
    }

    public final String getFullName() {
        return getLabel();
    }

    public final void setFullName(String fullName) {
        setLabel(fullName);
    }

    public Group getPrimaryGroup() {
        return primaryGroup;
    }

    public void setPrimaryGroup(Group primaryGroup) {
        this.primaryGroup = primaryGroup;
    }

    public void setPrimaryGroupId(int primaryGroupId) {
        (this.primaryGroup = new Group()).setId(primaryGroupId);
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public InetAddress getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(InetAddress lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

}
