package net.bodz.lily.schema.vapp;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _VApiCredit_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vapi_credit";

    public static final String FIELD_APP_ID = "app";
    public static final String FIELD_API_ID = "api";
    public static final String FIELD_CREDIT = "credit";

    public static final int N_APP_ID = 10;
    public static final int N_API_ID = 10;
    public static final int N_CREDIT = 20;

    private static final int _ord_APP_ID = 8;
    private static final int _ord_API_ID = _ord_APP_ID + 1;
    private static final int _ord_CREDIT = _ord_API_ID + 1;

    @NotNull
    BigDecimal credit;

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

    @Ordinal(_ord_CREDIT)
    @NotNull
    @Precision(value = N_CREDIT, scale = 4)
    @Column(name = "credit", nullable = false, precision = 20, scale = 4)
    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(@NotNull BigDecimal value) {
        this.credit = value;
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
            if (app.getId() == null)
                return 0;
            return app.getId();
        }
        return appId;
    }

    public synchronized void setAppId(int value) {
        this.appId = value;
    }

    public void initNotNulls() {
        this.credit = BigDecimal.ZERO;
    }

}
