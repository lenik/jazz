package net.bodz.lily.schema.vapp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _VApi_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vapi";

    public static final String FIELD_ID = "id";
    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_APP_ID = "app";
    public static final String FIELD_API_ID = "api";
    public static final String FIELD_CALLBACK = "callback";

    public static final int N_ID = 19;
    public static final int N_PROPERTIES = 2147483647;
    public static final int N_APP_ID = 10;
    public static final int N_API_ID = 10;
    public static final int N_CALLBACK = 200;

    private static final int _ord_ID = 1;
    private static final int _ord_PROPERTIES = _ord_ID + 4;
    private static final int _ord_APP_ID = _ord_PROPERTIES + 1;
    private static final int _ord_API_ID = _ord_APP_ID + 1;
    private static final int _ord_CALLBACK = _ord_API_ID + 1;

    @Id
    @NotNull
    long id;

    JsonVariant properties;

    String callback;

    /**  */
    @NotNull
    ApiType api;

    @NotNull
    int apiId;

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

    @Ordinal(_ord_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "props", precision = 2147483647)
    public JsonVariant getProperties() {
        return properties;
    }

    public void setProperties(JsonVariant value) {
        this.properties = value;
    }

    @Ordinal(_ord_CALLBACK)
    @Precision(value = N_CALLBACK)
    @TextInput(maxLength = N_CALLBACK)
    @Column(name = "callback", length = N_CALLBACK)
    public String getCallback() {
        return callback;
    }

    public void setCallback(String value) {
        this.callback = value;
    }

    /**
     *
     * @constraint foreign key (api) references lily.apitype (id)
     */
    @JoinColumn(name = "api")
    @ManyToOne
    @NotNull
    public ApiType getApi() {
        return api;
    }

    /**
     */
    public void setApi(@NotNull ApiType value) {
        this.api = value;
    }

    @Ordinal(_ord_API_ID)
    @Precision(value = 10)
    @Column(name = "api", nullable = false, precision = 10)
    public synchronized int getApiId() {
        if (api != null) {
            if (api.getId() == null)
                return 0;
            return api.getId();
        }
        return apiId;
    }

    public synchronized void setApiId(int value) {
        this.apiId = value;
    }

    /**
     *
     * @constraint foreign key (app) references lily.vapp (id)
     */
    @JoinColumn(name = "app")
    @ManyToOne
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
