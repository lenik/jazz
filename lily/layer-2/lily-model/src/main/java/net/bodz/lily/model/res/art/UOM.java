package net.bodz.lily.model.res.art;

import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.model.base.CoCode;

/**
 * 度量单位
 * 
 * 衡量单位的标准定义。
 * 
 * <p lang="en">
 * Unit Of Measurement
 */
public class UOM
        extends CoCode<UOM> {

    private static final long serialVersionUID = 1L;

    public static final int N_CODE = 20;
    public static final int N_PROPERTY = 20;

    private double scale;
    private String property = "数量";

    public UOM() {
    }

    public UOM(UOM o) {
        setId(o.getId());
        setCode(o.getCode());
        setLabel(o.getLabel());
        setDescription(o.getDescription());
        setParent(o.getParent());
        scale = o.scale;
        property = o.property;
    }

    public UOM(String code, String label, String property) {
        this(null, code, label, Double.NaN, null, property);
    }

    public UOM(String code, String label, double scale, UOM stdRef) {
        this(null, code, label, scale, stdRef, stdRef.property);
    }

    public UOM(String code, String label, double scale, UOM stdRef, String property) {
        this(null, code, label, scale, stdRef, property);
    }

    public UOM(int id, String code, String label, String property) {
        this(id, code, label, Double.NaN, null, property);
    }

    public UOM(int id, String code, String label, double scale, UOM stdRef) {
        this(id, code, label, scale, stdRef, stdRef.property);
    }

    public UOM(Integer id, String code, String label, double scale, UOM stdRef, String property) {
        if (code == null)
            throw new NullPointerException("code");
        if (label == null)
            throw new NullPointerException("label");
        if (property == null)
            throw new NullPointerException("property");
        setId(id);
        setCode(code);
        setLabel(label);
        setParent(stdRef);
        this.scale = scale;
        this.property = property;
    }

    public void setParentId(int parentId) {
        UOM parent = new UOM();
        parent.setId(parentId);
        setParent(parent);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * 属性名
     */
    @TextInput(maxLength = N_PROPERTY)
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

}
