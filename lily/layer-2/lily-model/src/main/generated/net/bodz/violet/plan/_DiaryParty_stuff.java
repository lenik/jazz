package net.bodz.violet.plan;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.security.User;

@IdType(Long.class)
public abstract class _DiaryParty_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "diary_party";

    public static final String FIELD_ID = "id";
    public static final String FIELD_DIARY_ID = "diary";
    public static final String FIELD_USER_ID = "user";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_ORG_ID = "org";
    public static final String FIELD_VALUE = "value";

    public static final int N_ID = 19;
    public static final int N_DIARY_ID = 19;
    public static final int N_USER_ID = 10;
    public static final int N_PERSON_ID = 10;
    public static final int N_ORG_ID = 10;
    public static final int N_VALUE = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_DIARY_ID = _ord_ID + 3;
    private static final int _ord_USER_ID = _ord_DIARY_ID + 1;
    private static final int _ord_PERSON_ID = _ord_USER_ID + 1;
    private static final int _ord_ORG_ID = _ord_PERSON_ID + 1;
    private static final int _ord_VALUE = _ord_ORG_ID + 1;

    @Id
    @NotNull
    long id;

    @NotNull
    int value;

    /**  */
    Person person;

    Integer personId;

    /**  */
    Organization org;

    Integer orgId;

    /** (User Account) */
    User user;

    Integer userId;

    /**  */
    @NotNull
    Diary diary;

    @NotNull
    long diaryId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    @Ordinal(_ord_VALUE)
    @Precision(value = 10)
    @Column(name = "value", nullable = false, precision = 10)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @label person
     * @constraint foreign key (person) references lily.person (id)
     */
    public Person getPerson() {
        return person;
    }

    /**
     */
    public void setPerson(Person value) {
        this.person = value;
    }

    @Ordinal(_ord_PERSON_ID)
    @Precision(value = N_PERSON_ID)
    @Column(name = "person", precision = 10)
    public synchronized Integer getPersonId() {
        if (person != null) {
            return person.getId();
        }
        return personId;
    }

    public synchronized void setPersonId(Integer value) {
        this.personId = value;
    }

    /**
     *
     * @label org
     * @constraint foreign key (org) references lily.org (id)
     */
    public Organization getOrg() {
        return org;
    }

    /**
     */
    public void setOrg(Organization value) {
        this.org = value;
    }

    @Ordinal(_ord_ORG_ID)
    @Precision(value = N_ORG_ID)
    @Column(name = "org", precision = 10)
    public synchronized Integer getOrgId() {
        if (org != null) {
            return org.getId();
        }
        return orgId;
    }

    public synchronized void setOrgId(Integer value) {
        this.orgId = value;
    }

    /**
     * {inheritDoc User}
     * User Account
     *
     * @label user
     * @constraint foreign key (user) references lily.user (id)
     */
    public User getUser() {
        return user;
    }

    /**
     * User Account
     */
    public void setUser(User value) {
        this.user = value;
    }

    @Ordinal(_ord_USER_ID)
    @Precision(value = N_USER_ID)
    @Column(name = "user", precision = 10)
    public synchronized Integer getUserId() {
        if (user != null) {
            return user.getId();
        }
        return userId;
    }

    public synchronized void setUserId(Integer value) {
        this.userId = value;
    }

    /**
     *
     * @label diary
     * @constraint foreign key (diary) references violet.diary (id)
     */
    @NotNull
    public Diary getDiary() {
        return diary;
    }

    /**
     */
    public void setDiary(@NotNull Diary value) {
        this.diary = value;
    }

    @Ordinal(_ord_DIARY_ID)
    @Precision(value = 19)
    @Column(name = "diary", nullable = false, precision = 19)
    public synchronized long getDiaryId() {
        if (diary != null) {
            if (diary.getId() == null)
                return 0L;
            return diary.getId();
        }
        return diaryId;
    }

    public synchronized void setDiaryId(long value) {
        this.diaryId = value;
    }

    public void initNotNulls() {
    }

}
