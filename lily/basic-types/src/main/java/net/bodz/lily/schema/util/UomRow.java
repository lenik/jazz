package net.bodz.lily.schema.util;

import javax.persistence.Table;

@Table(schema = UomRow.SCHEMA_NAME, name = UomRow.TABLE_NAME)
public class UomRow
        extends _UomRow_stuff {

    private static final long serialVersionUID = 1L;

    public UomRow() {
        super();
    }

    public UomRow(int id, String code, String label, double scale, Uom stdRef) {
        super(id, code, label, scale, stdRef);
    }

    public UomRow(int id, String code, String label, String property) {
        super(id, code, label, property);
    }

    public UomRow(Integer id, String code, String label, double scale, Uom stdRef, String property) {
        super(id, code, label, scale, stdRef, property);
    }

    public UomRow(String code, String label, double scale, Uom stdRef, String property) {
        super(code, label, scale, stdRef, property);
    }

    public UomRow(String code, String label, double scale, Uom stdRef) {
        super(code, label, scale, stdRef);
    }

    public UomRow(String code, String label, String property) {
        super(code, label, property);
    }

    public UomRow(Uom o) {
        super(o);
    }

}
