package net.bodz.lily.model.base.quota;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;
import net.bodz.lily.security.User;

/**
 * @label User Quota
 * @label.zh 用户配额
 */
@IdType(Integer.class)
public class UserQuota
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_QUOTA_CLASS = 80;
    public static final int N_SUMMARY = 30;

    private User user;
    private String quotaClass;
    private String summary;
    private double quota;

    /**
     * @label User
     * @label.zh 用户
     */
    @Priority(10)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @label Quota Class
     * @label.zh 配额类
     */
    @TextInput(maxLength = N_QUOTA_CLASS)
    @Priority(11)
    public String getQuotaClass() {
        return quotaClass;
    }

    public void setQuotaClass(String quotaClass) {
        this.quotaClass = quotaClass;
    }

    /**
     * @label Summary
     * @label.zh 汇总项
     */
    @TextInput(maxLength = N_SUMMARY)
    @Priority(12)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @label Quota
     * @label.zh 配额
     */
    @Priority(20)
    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

}
