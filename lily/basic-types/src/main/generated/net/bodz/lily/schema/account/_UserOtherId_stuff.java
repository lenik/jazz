package net.bodz.lily.schema.account;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

/**
 * User Open ID
 */
@IdType(Integer.class)
public abstract class _UserOtherId_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "useroid";

    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";
    public static final String FIELD_YEAR = "year";
    public static final String FIELD_USER_ID = "user";
    public static final String FIELD_TYPE_ID = "type";
    public static final String FIELD_OTHER_ID = "oid";
    public static final String FIELD_AUTH = "auth";

    public static final int N_BEGIN_TIME = 35;
    public static final int N_END_TIME = 35;
    public static final int N_YEAR = 10;
    public static final int N_USER_ID = 10;
    public static final int N_TYPE_ID = 10;
    public static final int N_OTHER_ID = 100;
    public static final int N_AUTH = 2147483647;

    private static final int _ord_BEGIN_TIME = 11;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_USER_ID = _ord_YEAR + 3;
    private static final int _ord_TYPE_ID = _ord_USER_ID + 1;
    private static final int _ord_OTHER_ID = _ord_TYPE_ID + 1;
    private static final int _ord_AUTH = _ord_OTHER_ID + 1;

    OffsetDateTime beginTime;

    OffsetDateTime endTime;

    @NotNull
    int year;

    /** The identity data */
    @NotNull
    String otherId;

    /** The authentication data */
    JsonVariant auth;

    /** Type of Open ID */
    @NotNull
    UserOtherIdType type;

    /** Type of Open ID */
    @NotNull
    int typeId;

    /** The declaring user */
    @NotNull
    User user;

    /** The declaring user */
    @NotNull
    int userId;

    @Ordinal(_ord_BEGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t0", precision = 35, scale = 6)
    public OffsetDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(OffsetDateTime value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetDateTime value) {
        this.endTime = value;
    }

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

    /**
     * The identity data
     */
    @Ordinal(_ord_OTHER_ID)
    @NotNull
    @Precision(value = N_OTHER_ID)
    @TextInput(maxLength = N_OTHER_ID)
    @Column(name = "oid", nullable = false, length = N_OTHER_ID)
    public String getOtherId() {
        return otherId;
    }

    /**
     * The identity data
     */
    public void setOtherId(@NotNull String value) {
        this.otherId = value;
    }

    /**
     * The authentication data
     */
    @Ordinal(_ord_AUTH)
    @Precision(value = 2147483647)
    @Column(name = "auth", precision = 2147483647)
    public JsonVariant getAuth() {
        return auth;
    }

    /**
     * The authentication data
     */
    public void setAuth(JsonVariant value) {
        this.auth = value;
    }

    /**
     * Type of Open ID
     *
     * @label Type
     * @constraint foreign key (type) references lily.useroidtype (id)
     */
    @JoinColumn(name = "type")
    @ManyToOne
    @NotNull
    public UserOtherIdType getType() {
        return type;
    }

    /**
     * Type of Open ID
     */
    public void setType(@NotNull UserOtherIdType value) {
        this.type = value;
    }

    /**
     * Type of Open ID
     */
    @Ordinal(_ord_TYPE_ID)
    @Precision(value = 10)
    @Column(name = "type", nullable = false, precision = 10)
    public synchronized int getTypeId() {
        if (type != null) {
            if (type.getId() == null)
                return 0;
            return type.getId();
        }
        return typeId;
    }

    /**
     * Type of Open ID
     */
    public synchronized void setTypeId(int value) {
        this.typeId = value;
    }

    /**
     * The declaring user
     *
     * @label User
     * @constraint foreign key (user) references lily.user (id)
     */
    @JoinColumn(name = "user")
    @ManyToOne
    @NotNull
    public User getUser() {
        return user;
    }

    /**
     * The declaring user
     */
    public void setUser(@NotNull User value) {
        this.user = value;
    }

    /**
     * The declaring user
     */
    @Ordinal(_ord_USER_ID)
    @Precision(value = 10)
    @Column(name = "user", nullable = false, precision = 10)
    public synchronized int getUserId() {
        if (user != null) {
            if (user.getId() == null)
                return 0;
            return user.getId();
        }
        return userId;
    }

    /**
     * The declaring user
     */
    public synchronized void setUserId(int value) {
        this.userId = value;
    }

    public void initNotNulls() {
        this.otherId = "";
    }

}
