package net.bodz.lily.security.impl;

import net.bodz.bas.c.string.StringUtil;

/**
 * @see net.bodz.lily.security.User
 */
public class UserMask
        extends CoPrincipalMask {

    public String password;
    public String email;
    public String mobile;

    public String getCodeName1() {
        String s = getCodeName();
        if (s != null)
            s = StringUtil.enc1(s);
        return s;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
