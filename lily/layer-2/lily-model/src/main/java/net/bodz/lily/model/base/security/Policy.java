package net.bodz.lily.model.base.security;

import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.model.base.IdType;

/**
 * @label Policy
 * @label.zh 系统配额
 */
@IdType(Integer.class)
public class Policy
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_CONTROL_CLASS = 80;
    public static final int N_METHOD = 40;

    private Group group;
    private User user;
    private String controlClass;
    private String method;
    private int allowBits;
    private int denyBits;

    /**
     * @label Group
     * @label.zh 用户组
     */
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @label User
     * @label.zh 用户
     */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @label Controlled Class
     * @label.zh 受控类
     */
    @TextInput(maxLength = N_CONTROL_CLASS)
    public String getControlClass() {
        return controlClass;
    }

    public void setControlClass(String controlClass) {
        this.controlClass = controlClass;
    }

    /**
     * @label Method
     * @label.zh 方法
     */
    @TextInput(maxLength = N_METHOD)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @label Allow Bits
     * @label.zh 允许位
     */
    public int getAllowBits() {
        return allowBits;
    }

    public void setAllowBits(int allowBits) {
        this.allowBits = allowBits;
    }

    /**
     * @label Deny Bits
     * @label.zh 拒绝位
     */
    public int getDenyBits() {
        return denyBits;
    }

    public void setDenyBits(int denyBits) {
        this.denyBits = denyBits;
    }

}
