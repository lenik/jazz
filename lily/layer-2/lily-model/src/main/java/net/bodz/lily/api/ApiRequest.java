package net.bodz.lily.api;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@Table(name = "apply_api")
@IdType(Long.class)
public class ApiRequest
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    AppApply apply;
    Api api;

    public ApiRequest() {
    }

    public AppApply getApply() {
        return apply;
    }

    public void setApply(AppApply apply) {
        this.apply = apply;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

}
