package net.bodz.violet.schema.fab;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _FabStdTester_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabstdtester";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CMDLINE = "cmdline";

    public static final int N_ID = 10;
    public static final int N_CMDLINE = 200;

    private static final int _ord_ID = 1;
    private static final int _ord_CMDLINE = 15;

    @Id
    @NotNull
    int id;

    String cmdline;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Ordinal(_ord_CMDLINE)
    @Precision(value = N_CMDLINE)
    @TextInput(maxLength = N_CMDLINE)
    @Column(name = "cmdline", length = N_CMDLINE)
    public String getCmdline() {
        return cmdline;
    }

    public void setCmdline(String value) {
        this.cmdline = value;
    }

    public void initNotNulls() {
    }

}
