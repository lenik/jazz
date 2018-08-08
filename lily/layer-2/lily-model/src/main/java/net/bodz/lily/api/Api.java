package net.bodz.lily.api;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCode;

@Table(name = "api")
@IdType(Integer.class)
public class Api
        extends CoCode<Api> {

    private static final long serialVersionUID = 1L;

    String uom;

    public Api() {
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

}
