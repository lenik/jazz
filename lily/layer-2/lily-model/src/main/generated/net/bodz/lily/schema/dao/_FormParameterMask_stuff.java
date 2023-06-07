package net.bodz.lily.schema.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _FormParameterMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    Integer formId;
    IntegerRange formIdRange = new IntegerRange();

    String uniqName;
    String uniqNamePattern;

    String value;
    String valuePattern;

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

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer value) {
        this.formId = value;
    }

    public IntegerRange getFormIdRange() {
        return formIdRange;
    }

    public void setFormIdRange(IntegerRange range) {
        this.formIdRange = range;
    }

    public String getUniqName() {
        return uniqName;
    }

    public void setUniqName(String value) {
        this.uniqName = value;
    }

    public String getUniqNamePattern() {
        return uniqNamePattern;
    }

    public void setUniqNamePattern(String value) {
        this.uniqNamePattern = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValuePattern() {
        return valuePattern;
    }

    public void setValuePattern(String value) {
        this.valuePattern = value;
    }

}