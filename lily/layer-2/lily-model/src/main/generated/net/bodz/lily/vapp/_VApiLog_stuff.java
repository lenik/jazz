package net.bodz.lily.vapp;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _VApiLog_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vapi_log";

    public static final String FIELD_ID = "id";
    public static final String FIELD_APP_ID = "app";
    public static final String FIELD_API_ID = "api";
    public static final String FIELD_MESSAGE = "message";
    public static final String FIELD_ERR = "err";

    public static final int N_ID = 19;
    public static final int N_APP_ID = 10;
    public static final int N_API_ID = 10;
    public static final int N_MESSAGE = 2147483647;
    public static final int N_ERR = 2147483647;

    private static final int _ord_ID = 1;
    private static final int _ord_APP_ID = _ord_ID + 9;
    private static final int _ord_API_ID = _ord_APP_ID + 1;
    private static final int _ord_MESSAGE = _ord_API_ID + 1;
    private static final int _ord_ERR = _ord_MESSAGE + 1;

    @Id
    @NotNull
    long id;

    String message;

    String err;

    /**  */
    ApiType api;

    Integer apiId;

    /**  */
    @NotNull
    VApp app;

    @NotNull
    int appId;

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

    @Ordinal(_ord_MESSAGE)
    @Precision(value = N_MESSAGE)
    @TextInput(maxLength = N_MESSAGE)
    @Column(name = "message", length = N_MESSAGE)
    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    @Ordinal(_ord_ERR)
    @Precision(value = N_ERR)
    @TextInput(maxLength = N_ERR)
    @Column(name = "err", length = N_ERR)
    public String getErr() {
        return err;
    }

    public void setErr(String value) {
        this.err = value;
    }

    /**
     *
     * @label api
     * @constraint foreign key (api) references lily.apitype (id)
     */
    public ApiType getApi() {
        return api;
    }

    /**
     */
    public void setApi(ApiType value) {
        this.api = value;
    }

    @Ordinal(_ord_API_ID)
    @Precision(value = N_API_ID)
    @Column(name = "api", precision = 10)
    public synchronized Integer getApiId() {
        if (api != null) {
            return api.getId();
        }
        return apiId;
    }

    public synchronized void setApiId(Integer value) {
        this.apiId = value;
    }

    /**
     *
     * @label app
     * @constraint foreign key (app) references lily.vapp (id)
     */
    @NotNull
    public VApp getApp() {
        return app;
    }

    /**
     */
    public void setApp(@NotNull VApp value) {
        this.app = value;
    }

    @Ordinal(_ord_APP_ID)
    @Precision(value = 10)
    @Column(name = "app", nullable = false, precision = 10)
    public synchronized int getAppId() {
        if (app != null) {
            return app.getId();
        }
        return appId;
    }

    public synchronized void setAppId(int value) {
        this.appId = value;
    }

    public void initNotNulls() {
    }

}
