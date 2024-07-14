package net.bodz.lily.schema.io;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _Storage_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "storage";

    public static final String FIELD_NAME = "name";

    public static final int N_NAME = 30;

    private static final int _ord_NAME = 12;

    @NotNull
    String name;

    @Ordinal(_ord_NAME)
    @NotNull
    @Precision(value = N_NAME)
    @TextInput(maxLength = N_NAME)
    @Column(name = "name", nullable = false, length = N_NAME)
    public String getName() {
        return name;
    }

    public void setName(@NotNull String value) {
        this.name = value;
    }

    public void initNotNulls() {
        this.name = "";
    }

}
