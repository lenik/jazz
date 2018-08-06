package net.bodz.lily.security;

import java.net.InetAddress;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.bas.meta.decl.Redundant;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;

@Table(name = "user_run")
@IdType(Long.class)
public class UserRun
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    static final int ONLINE = 1;
    static final int OFFLINE = 2;

    User user;
    int score;

    long lastLoginTime;
    InetAddress lastLoginIP; // =Inet4Address.getByAddress();

    public UserRun() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Redundant
    public String getStateText() {
        return super.getLabel();
    }

    @Redundant
    public DateTime getActiveTime() {
        return super.getLastModifiedDate();
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

}
