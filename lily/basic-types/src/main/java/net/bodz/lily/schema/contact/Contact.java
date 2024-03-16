package net.bodz.lily.schema.contact;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.i18n.geo.GeoZone;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.concrete.IHaveProperties;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.repr.EntGroup;
import net.bodz.lily.schema.geo.Zone;

/**
 * 联系方式
 */
@Table(name = "contact")
@IdType(Integer.class)
public class Contact
        extends IdEntity<Integer>
        implements
            IContactAddress,
            IHaveProperties {

    private static final long serialVersionUID = 1L;

    public static final int N_NAME = 30;
    public static final int N_USAGE = 10;
    public static final int N_TEL = 20;
    public static final int N_MOBILE = 20;
    public static final int N_FAX = 20;
    public static final int N_EMAIL = 30;
    public static final int N_WEB = 80;
    public static final int N_QQ = 20;
    public static final int N_WECHAT = 30;

    private Organization org;
    private OrgUnit orgUnit;
    private Person person;

    private String rename;
    private String usage;

    private Zone zone;
    private Integer zoneId;

    private String country = "cn";
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String address1 = "";
    private String address2 = "";
    private String postalCode;

    private String tel;
    private String mobile;
    private String fax;
    private String email;
    private String web;
    private String qq;
    private String wechat;

    private boolean telValidated;
    private boolean mobileValidated;
    private boolean faxValidated;
    private boolean emailValidated;
    private boolean webValidated;
    private boolean qqValidated;
    private boolean wechatValidated;

    private JsonVariant properties;

    /**
     * 公司/单位
     */
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    /**
     * 部门/科室
     */
    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    /**
     * 联系人
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Contact name. Could be different to the real name.
     *
     * <code>null</code> if there is a real name and the contact name should be the real name.
     *
     * @label Alternative Name
     * @label.zh 别名
     */
    @TextInput(maxLength = N_NAME)
    public String getRename() {
        return rename;
    }

    public void setRename(String rename) {
        this.rename = rename;
    }

    /**
     * The usage name, could be "work", "home", in locale language.
     *
     * <code>null</code> for the default/common usage.
     *
     * @label Usage
     * @label.zh 用途
     */
    @TextInput(maxLength = N_USAGE)
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    /**
     * 区域
     */
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(EntGroup.Position.class)
    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Integer getZoneId() {
        return zone != null ? (Integer) zone.getId() : zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getR1() {
        return r1;
    }

    @Override
    public void setR1(String r1) {
        this.r1 = r1;
    }

    @Override
    public String getR2() {
        return r2;
    }

    @Override
    public void setR2(String r2) {
        this.r2 = r2;
    }

    @Override
    public String getR3() {
        return r3;
    }

    @Override
    public void setR3(String r3) {
        this.r3 = r3;
    }

    @Override
    public String getR4() {
        return r4;
    }

    @Override
    public void setR4(String r4) {
        this.r4 = r4;
    }

    @Override
    public String getAddress1() {
        return address1;
    }

    @Override
    public void setAddress1(String address1) {
        if (address1 == null)
            throw new NullPointerException("address1");
        this.address1 = address1;
    }

    @Override
    public String getAddress2() {
        return address2;
    }

    @Override
    public void setAddress2(String address2) {
        if (address2 == null)
            throw new NullPointerException("address2");
        this.address2 = address2;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @label 固定电话
     */
    @OfGroup(EntGroup.Communication.class)
    @TextInput(maxLength = N_TEL)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @label 移动电话
     */
    @OfGroup(EntGroup.Communication.class)
    @TextInput(maxLength = N_MOBILE)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @label 传真
     */
    @OfGroup(EntGroup.Communication.class)
    @TextInput(maxLength = N_FAX)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @label E-mail
     */
    @OfGroup(EntGroup.Communication.class)
    @TextInput(maxLength = N_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @TextInput(maxLength = N_WEB)
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * @label QQ
     */
    @OfGroup(EntGroup.Communication.class)
    @TextInput(maxLength = N_QQ)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @label WeChat
     */
    @OfGroup(EntGroup.Communication.class)
    @TextInput(maxLength = N_WECHAT)
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public boolean isTelValidated() {
        return telValidated;
    }

    public void setTelValidated(boolean telValidated) {
        this.telValidated = telValidated;
    }

    public boolean isMobileValidated() {
        return mobileValidated;
    }

    public void setMobileValidated(boolean mobileValidated) {
        this.mobileValidated = mobileValidated;
    }

    public boolean isFaxValidated() {
        return faxValidated;
    }

    public void setFaxValidated(boolean faxValidated) {
        this.faxValidated = faxValidated;
    }

    public boolean isEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(boolean emailValidated) {
        this.emailValidated = emailValidated;
    }

    public boolean isWebValidated() {
        return webValidated;
    }

    public void setWebValidated(boolean webValidated) {
        this.webValidated = webValidated;
    }

    public boolean isQqValidated() {
        return qqValidated;
    }

    public void setQqValidated(boolean qqValidated) {
        this.qqValidated = qqValidated;
    }

    public boolean isWechatValidated() {
        return wechatValidated;
    }

    public void setWechatValidated(boolean wechatValidated) {
        this.wechatValidated = wechatValidated;
    }

    /**
     * Get the full address.
     *
     * @label 地址
     */
    @OfGroup(EntGroup.Position.class)
    @Derived
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder(80);
        if (zone != null) {
            GeoZone geoZone = zone.geo.getGeoZone();
            if (geoZone != null) {
                String zhString = geoZone.toZhString();
                sb.append(zhString);
                sb.append(" ");
            }
        }
        if (address1 != null) {
            sb.append(address1);
            sb.append(" ");
        }
        if (address2 != null) {
            sb.append(address2);
            sb.append(" ");
        }
        String addr = sb.toString();
        return addr.trim();
    }

    /**
     * Get telephone numbers.
     *
     * @label 电话/手机
     */
    @OfGroup(EntGroup.Communication.class)
    @Derived
    public String getTels() {
        List<String> tels = new ArrayList<>();
        tels.add(tel);
        tels.add(mobile);
        // tels.add(fax);

        StringBuilder sb = new StringBuilder();
        for (String tel : tels)
            if (tel != null)
                if (! tel.isEmpty()) {
                    if (sb.length() != 0)
                        sb.append(", ");
                    sb.append(tel);
                }
        return sb.toString();
    }

    @Override
    public JsonVariant getProperties() {
        return properties;
    }

    @Override
    public void setProperties(JsonVariant properties) {
        this.properties = properties;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        org = o.readInto("org", org, new Organization());
        orgUnit = o.readInto("orgUnit", orgUnit, new OrgUnit());
        person = o.readInto("person", person, new Person());

        rename = o.getString("rename", rename);
        usage = o.getString("usage", usage);

        IContactAddress.super.jsonIn(o, opts);

        tel = o.getString("tel", tel);
        mobile = o.getString("mobile", mobile);
        fax = o.getString("fax", fax);
        email = o.getString("email", email);
        web = o.getString("web", web);
        qq = o.getString("qq", qq);
        wechat = o.getString("wechat", wechat);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (usage != null)
            sb.append("[" + usage + "]");

        if (zone != null)
            sb.append(zone);
        else {
            sb.append(format(true)); // top-down
        }

        if (tel != null)
            sb.append("\nTel: " + tel);
        if (mobile != null)
            sb.append("\nMobile: " + mobile);
        if (fax != null)
            sb.append("\nFax: " + fax);
        if (email != null)
            sb.append("\nEmail: " + email);
        if (web != null)
            sb.append("\nWeb: " + web);
        if (qq != null)
            sb.append("\nQQ: " + qq);
        if (wechat != null)
            sb.append("\nWeChat: " + qq);

        return sb.toString();
    }

}
