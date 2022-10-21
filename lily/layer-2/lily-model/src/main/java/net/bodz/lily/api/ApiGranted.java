package net.bodz.lily.api;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@Table(name = "app_api")
@IdType(Long.class)
public class ApiGranted
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    App app;
    Api api;
    int mode = -1;

    public ApiGranted() {
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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
