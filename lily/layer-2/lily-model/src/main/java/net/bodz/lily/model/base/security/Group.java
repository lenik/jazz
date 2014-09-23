package net.bodz.lily.model.base.security;

import net.bodz.lily.model.base.CoEntity;

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
 * @label.zh.cn 用户组（角色）
 * 
 * @rel user: 管理用户帐户
 * @rel orgunit: 管理组织单元（部门、车间）
 * @rel org: 管理联系人（组织、企业）
 * 
 * @see <a href="http://www.williamlong.info/archives/2937.html">个人密码安全策略</a>
 * @see <a href="http://wenku.baidu.com/view/e8638601eff9aef8941e065e.html">用户名大全</a>
 */
public class Group
        extends CoEntity {

    private static final long serialVersionUID = 1L;

    public static final int N_LOGIN_NAME = 30;
    public static final int N_FULL_NAME = 40;

    private int id;

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

}
