package net.bodz.lily.contact;

import java.sql.Date;
import java.util.*;

import net.bodz.bas.c.java.util.TimeZones;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.site.file.UploadHint;
import net.bodz.bas.t.order.PriorityUtils;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.attachment.AttachmentPathChangeEvent;
import net.bodz.lily.entity.attachment.IAttachment;
import net.bodz.lily.entity.attachment.IAttachmentListing;
import net.bodz.lily.entity.attachment.IHaveAttachments;
import net.bodz.lily.model.base.IdEntity;

/**
 * 参与方
 */
@IdType(Integer.class)
@UploadHint
public abstract class Party
        extends IdEntity<Integer>
        implements
            IHaveAttachments {

    private static final long serialVersionUID = 1L;

    public static final int N_BANK = 50;
    public static final int N_ACCOUNT = 30;
    public static final int N_SUBJECT = 200;
    public static final int N_LANGTAG = 5;
    public static final int N_TIMEZONEID = 10;

    private PartyCategory category;

    private Date birthday;
    private Locale locale = Locale.SIMPLIFIED_CHINESE;
    private TimeZone timeZone = TimeZones.TZ_SHANGHAI;

    private boolean peer;
    private boolean customer;
    private boolean supplier;
    private final Set<String> tags = new TreeSet<>();

    private String subject;
    private List<Contact> contacts = new ArrayList<Contact>();

    private String bank;
    private String account;

    private PartyProperties properties = new PartyProperties();

    /**
     * 分类
     */
    public PartyCategory getCategory() {
        return category;
    }

    public void setCategory(PartyCategory category) {
        this.category = category;
    }

    /**
     * 生日
     */
    @Priority(100)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 语言/区域
     */
    @OfGroup(StdGroup.Settings.class)
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        if (locale == null)
            throw new NullPointerException("locale");
        this.locale = locale;
    }

    /**
     * 语言代码
     */
    @OfGroup(StdGroup.Settings.class)
    @TextInput(maxLength = N_LANGTAG)
    @Derived
    public String getLangTag() {
        return locale.toLanguageTag();
    }

    public void setLangTag(String langTag) {
        if (langTag == null)
            throw new NullPointerException("langTag");
        this.locale = Locale.forLanguageTag(langTag);
    }

    /**
     * 时区
     */
    @OfGroup(StdGroup.Settings.class)
    @TextInput(maxLength = N_TIMEZONEID)
    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        if (timeZone == null)
            throw new NullPointerException("timeZone");
        this.timeZone = timeZone;
    }

    /**
     * 时区ID
     */
    @OfGroup(StdGroup.Settings.class)
    @Derived
    public String getTimeZoneId() {
        return timeZone.getID();
    }

    public void setTimeZoneId(String timeZoneId) {
        if (timeZoneId == null)
            throw new NullPointerException("timeZoneId");
        timeZone = TimeZone.getTimeZone(timeZoneId);
    }

    /**
     * 标记 - 同行
     */
    @OfGroup(StdGroup.Classification.class)
    public boolean isPeer() {
        return peer;
    }

    public void setPeer(boolean peer) {
        this.peer = peer;
    }

    /**
     * 标记 - 客户
     */
    @OfGroup(StdGroup.Classification.class)
    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    /**
     * 标记 - 供应商
     */
    @OfGroup(StdGroup.Classification.class)
    public boolean isSupplier() {
        return supplier;
    }

    public void setSupplier(boolean supplier) {
        this.supplier = supplier;
    }

    /**
     * 标签集
     */
    @OfGroup(StdGroup.Classification.class)
    public Set<String> getTags() {
        return tags;
    }

    /**
     * 兴趣方向
     *
     * 个人的兴趣爱好或公司的主营业务。
     */
    @Priority(300)
    @TextInput(maxLength = N_SUBJECT)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 联系方式
     */
    @Priority(501)
    @DetailLevel(DetailLevel.NORMAL)
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        if (contacts == null)
            throw new NullPointerException("contacts");
        this.contacts = contacts;
    }

    /**
     * 默认联系方式
     *
     * 当不特别指定时，默认使用的联系方式。 （通常是较常用的联系方式。）
     */
    @Priority(500)
    @Derived
    public Contact getContact0() {
        if (contacts.isEmpty())
            return null;
        else
            return PriorityUtils.selectTop(contacts);
    }

    @Priority(500)
    @Derived
    public Contact getContact() {
        if (contacts.isEmpty())
            contacts.add(new Contact());
        Contact contact = contacts.get(0);
        return contact;
    }

    public void setContact(Contact contact) {
        if (contact == null)
            contact = new Contact();
        contacts.clear();
        contacts.add(contact);
    }

    /**
     * 银行
     *
     * @placeholder 输入银行名称...
     */
    @Priority(400)
    @TextInput(maxLength = N_BANK)
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 银行帐号
     *
     * @placeholder 输入银行帐号...
     */
    @Priority(401)
    @TextInput(maxLength = N_ACCOUNT)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 由一系列单字符描述的分类特征。
     *
     * @label Characters
     * @label.zh 特征字
     */
    @OfGroup(StdGroup.Classification.class)
    @Derived
    public String getTypeChars() {
        StringBuilder sb = new StringBuilder();
        if (isPeer())
            sb.append("同");
        if (isCustomer())
            sb.append("客");
        if (isSupplier())
            sb.append("供");
        return sb.toString();
    }

    @Override
    public PartyProperties getProperties() {
        return properties;
    }

    @Override
    public IAttachmentListing listAttachments() {
        return new IAttachmentListing() {

            @Override
            public void onAttachmentPathChanged(AttachmentPathChangeEvent event) {
                event.getNewVolume();
                event.getNewPath();
            }

            @Override
            public Collection<IAttachment> getAttachments(String category) {
                switch (category) {
                case IMAGE:
                    return properties.getImages();
                }
                return null;
            }

        };
    }

}
