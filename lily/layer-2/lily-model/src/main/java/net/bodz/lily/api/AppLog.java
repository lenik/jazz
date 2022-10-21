package net.bodz.lily.api;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@Table(name = "applog")
@IdType(Long.class)
public class AppLog
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    App app;
    Api api;
    String error;
    String message;

    public AppLog() {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Derived
    public int getVerboseLevel() {
        return getPriority();
    }

}
