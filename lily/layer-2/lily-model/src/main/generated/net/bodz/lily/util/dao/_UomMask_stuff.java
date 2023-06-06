package net.bodz.lily.util.dao;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _UomMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    Object properties;

    String prop;
    String propPattern;

    Integer stdId;
    IntegerRange stdIdRange = new IntegerRange();

    Double scale;
    DoubleRange scaleRange = new DoubleRange();

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public IntegerRange getIdRange() {
        return idRange;
    }

    public void setIdRange(IntegerRange range) {
        this.idRange = range;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String value) {
        this.prop = value;
    }

    public String getPropPattern() {
        return propPattern;
    }

    public void setPropPattern(String value) {
        this.propPattern = value;
    }

    public Integer getStdId() {
        return stdId;
    }

    public void setStdId(Integer value) {
        this.stdId = value;
    }

    public IntegerRange getStdIdRange() {
        return stdIdRange;
    }

    public void setStdIdRange(IntegerRange range) {
        this.stdIdRange = range;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double value) {
        this.scale = value;
    }

    public DoubleRange getScaleRange() {
        return scaleRange;
    }

    public void setScaleRange(DoubleRange range) {
        this.scaleRange = range;
    }

}
