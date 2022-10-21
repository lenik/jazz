package net.bodz.lily.api;

import java.math.BigDecimal;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@IdType(Integer.class)
public class AppAccount
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    App app;
    Api api;
    AppAccountType type = AppAccountTypes.USE;
    BigDecimal quantity = BigDecimal.ZERO;

    public AppAccount() {
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

    public AppAccountType getType() {
        return type;
    }

    public void setType(AppAccountType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        if (quantity == null)
            throw new NullPointerException("quantity");
        this.quantity = quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = BigDecimal.valueOf(quantity);
    }

}
