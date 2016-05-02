package net.bodz.lily.model.base.quota;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @label System Quota
 * @label.zh 系统配额
 */
@IdType(Integer.class)
public class SysQuota
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_QUOTA_CLASS = 80;
    public static final int N_SUMMARY = 30;

    private String quotaClass;
    private String summary;
    private double quota;
    private Double userQuota;

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

    /**
     * @label User Quota
     * @label.zh 用户配额
     */
    @Priority(21)
    public Double getUserQuota() {
        return userQuota;
    }

    public void setUserQuota(Double userQuota) {
        this.userQuota = userQuota;
    }

}
