package net.bodz.lily.schema.util;

import javax.persistence.Table;

/**
 * 度量单位
 *
 * 衡量单位的标准定义。
 *
 * <p lang="en">
 * Unit Of Measurement
 */
@Table(schema = Uom.SCHEMA_NAME, name = Uom.TABLE_NAME)
public class Uom
        extends _Uom_stuff<Uom> {

    private static final long serialVersionUID = 1L;

    public Uom() {
    }

    public Uom(Uom o) {
        id(o.id());
        setCode(o.getCode());
        setLabel(o.getLabel());
        setDescription(o.getDescription());
        setParent(o.getParent());
        scale = o.scale;
//        this.prop = o.property;
    }

    public Uom(String code, String label, String property) {
        this(null, code, label, Double.NaN, null, property);
    }

    public Uom(String code, String label, double scale, Uom stdRef) {
        this(null, code, label, scale, stdRef, stdRef.property);
    }

    public Uom(String code, String label, double scale, Uom stdRef, String property) {
        this(null, code, label, scale, stdRef, property);
    }

    public Uom(int id, String code, String label, String property) {
        this(id, code, label, Double.NaN, null, property);
    }

    public Uom(int id, String code, String label, double scale, Uom stdRef) {
        this(id, code, label, scale, stdRef, stdRef.property);
    }

    public Uom(Integer id, String code, String label, double scale, Uom stdRef, String property) {
        if (code == null)
            throw new NullPointerException("code");
        if (label == null)
            throw new NullPointerException("label");
        if (property == null)
            throw new NullPointerException("property");
        id(id);
        setCode(code);
        setLabel(label);
        setParent(stdRef);
        this.scale = scale;
        this.property = property;
    }

}
