package net.bodz.lily.concrete;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.schema.util.Uom;

@FieldGroupVue
@IdType(Integer.class)
@TsTyped
@TypeParameters({ TypeParamType.THIS_REC })
public abstract class CoParameter<self_t extends CoParameter<self_t>>
        extends CoCode<self_t> {

    private static final long serialVersionUID = 1L;

    String name;
    String type;
    boolean optional;
    Uom uom;
    Integer uomId;
    String values;

    public CoParameter() {
        super();
    }

    public CoParameter(self_t parent) {
        super(parent);
    }

    @Override
    public String getUniqueName() {
        return getName();
    }

    @Override
    public void setUniqueName(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    public synchronized Integer getUomId() {
        return uom != null ? uom.id() : uomId;
    }

    public synchronized void setUomId(Integer id) {
        uom = null;
        uomId = id;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

}
