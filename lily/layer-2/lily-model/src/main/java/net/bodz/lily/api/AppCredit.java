package net.bodz.lily.api;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "appcred")
@IdType(Integer.class)
public class AppCredit
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    App app;
    Api api;
    BigDecimal credit = BigDecimal.ZERO;

    public AppCredit() {
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public void setCredit(double credit) {
        this.credit = BigDecimal.valueOf(credit);
    }

}
