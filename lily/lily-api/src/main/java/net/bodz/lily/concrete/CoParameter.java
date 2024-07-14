package net.bodz.lily.concrete;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.meta.TsTyped;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.schema.util.Uom;

@FieldGroupVue
@IdType(Integer.class)
@TsTyped
@TypeParameters({ TypeParamType.THIS_REC })
public abstract class CoParameter<self_t extends CoParameter<self_t>>
        extends CoCode<self_t> {

    private static final long serialVersionUID = 1L;

    String type;
    boolean optional;
    Uom uom;
    String values;

    public CoParameter() {
        super();
    }

    public CoParameter(self_t parent) {
        super(parent);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public Uom getUom() {
        return uom;
    }

    public void setUom(Uom uom) {
        this.uom = uom;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

}
