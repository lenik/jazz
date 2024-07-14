package net.bodz.lily.schema.util;

import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _UomRow_stuff
        extends Uom {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "uom";

    public _UomRow_stuff() {
        super();
    }

    public _UomRow_stuff(int id, String code, String label, double scale, Uom stdRef) {
        super(id, code, label, scale, stdRef);
    }

    public _UomRow_stuff(int id, String code, String label, String property) {
        super(id, code, label, property);
    }

    public _UomRow_stuff(Integer id, String code, String label, double scale, Uom stdRef, String property) {
        super(id, code, label, scale, stdRef, property);
    }

    public _UomRow_stuff(String code, String label, double scale, Uom stdRef, String property) {
        super(code, label, scale, stdRef, property);
    }

    public _UomRow_stuff(String code, String label, double scale, Uom stdRef) {
        super(code, label, scale, stdRef);
    }

    public _UomRow_stuff(String code, String label, String property) {
        super(code, label, property);
    }

    public _UomRow_stuff(Uom o) {
        super(o);
    }

    @Override
    public void initNotNulls() {
    }

}
