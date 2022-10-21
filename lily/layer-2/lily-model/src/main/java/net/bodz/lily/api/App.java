package net.bodz.lily.api;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@Table(name = "app")
@IdType(Integer.class)
public class App
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    AppApply apply;
    String secret;

    public App() {
    }

    public AppApply getApply() {
        return apply;
    }

    public void setApply(AppApply apply) {
        this.apply = apply;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
