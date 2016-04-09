package net.bodz.lily.model.res.people;

import java.sql.Date;

import net.bodz.bas.html.meta.Radio;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;

/**
 * 联系人
 */
public class Person
        extends Party {

    private static final long serialVersionUID = 1L;

    public static final int N_HOMELAND = 10;
    public static final int N_PASSPORT = 20;
    public static final int N_SOCIAL_SECURITY_NUM = 20;
    public static final int N_DRIVER_LICENSE_NUM = 20;

    private Gender gender = Gender.UNKNOWN;
    private boolean employee;
    private String homeland;
    private String passport;
    private String socialSecurityNum;
    private String driverLicenseNum;

    /**
     * 姓名
     */
    @OfGroup(StdGroup.Identity.class)
    @Derived
    public String getFullName() {
        return getLabel();
    }

    public void setFullName(String fullName) {
        setLabel(fullName);
    }

    /**
     * 兴趣爱好
     */
    @Priority(300)
    @TextInput(maxLength = N_SUBJECT)
    @Override
    public String getSubject() {
        return super.getSubject();
    }

    /**
     * 性别
     * 
     * @TODO view builder criteria.
     */
    @Radio
    @OfGroup(StdGroup.Classification.class)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null)
            throw new NullPointerException("gender");
        this.gender = gender;
    }

    /**
     * 标记 - 雇员
     */
    @OfGroup(StdGroup.Classification.class)
    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    /**
     * 籍贯
     */
    @OfGroup(StdGroup.Classification.class)
    @TextInput(maxLength = N_HOMELAND)
    public String getHomeland() {
        return homeland;
    }

    public void setHomeland(String homeland) {
        this.homeland = homeland;
    }

    /**
     * 护照
     */
    @OfGroup(StdGroup.Identity.class)
    @TextInput(maxLength = N_PASSPORT)
    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * 身份证
     */
    @OfGroup(StdGroup.Identity.class)
    @TextInput(maxLength = N_SOCIAL_SECURITY_NUM)
    public String getSocialSecurityNum() {
        return socialSecurityNum;
    }

    public void setSocialSecurityNum(String socialSecurityNum) {
        this.socialSecurityNum = socialSecurityNum;
    }

    /**
     * 驾照
     */
    @OfGroup(StdGroup.Identity.class)
    @TextInput(maxLength = N_DRIVER_LICENSE_NUM)
    public String getDriverLicenseNum() {
        return driverLicenseNum;
    }

    public void setDriverLicenseNum(String driverLicenseNum) {
        this.driverLicenseNum = driverLicenseNum;
    }

    /**
     * @label A/S/L
     * @label.zh 年龄/性/籍
     */
    @OfGroup(StdGroup.Classification.class)
    @Derived
    public String getAgeSexLoc() {
        StringBuilder sb = new StringBuilder();

        Date birthday = getBirthday();
        if (birthday != null) {
            long age_ms = System.currentTimeMillis() - getBirthday().getTime();
            long age_yr = age_ms / 1000L / 86400L / 365L;
            sb.append(age_yr);
        } else {
            sb.append('-');
        }

        sb.append('/');
        sb.append(gender.getLabel());

        if (homeland != null) {
            sb.append('/');
            sb.append(homeland);
        }
        return sb.toString();
    }

    // TODO xjdoc don't inherit the docs from the super method.
    /**
     * 由一系列单字符描述的分类特征。
     * 
     * @label Characters
     * @label.zh 特征字
     */
    @OfGroup(StdGroup.Classification.class)
    @Derived
    @Override
    public String getTypeChars() {
        String typeChars = super.getTypeChars();
        if (employee)
            typeChars += "雇";
        return typeChars;
    }

}
