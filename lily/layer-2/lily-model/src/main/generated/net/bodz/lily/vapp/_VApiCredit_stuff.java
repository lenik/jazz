package net.bodz.lily.vapp;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _VApiCredit_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_CREDIT = 20;

    private static final int _ord_ID = 1;
    private static final int _ord_APP_ID = _ord_ID + 7;
    private static final int _ord_API_ID = _ord_APP_ID + 1;
    private static final int _ord_CREDIT = _ord_API_ID + 1;

    @Id
    @NotNull
    int id;

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

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

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
     * @label api
     * @constraint foreign key (api) references lily.apitype (id)
     */
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
            return api.getId();
        }
        return apiId;
    }

    public synchronized void setApiId(int value) {
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
        this.credit = BigDecimal.ZERO;
    }

}
