package net.bodz.violet.schema.fab;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _FabProcessSerial_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabproc_sn";

    public static final String FIELD_ID = "id";
    public static final String FIELD_PROCESS_ID = "proc";
    public static final String FIELD_SERIAL = "serial";

    public static final int N_ID = 19;
    public static final int N_PROCESS_ID = 19;
    public static final int N_SERIAL = 40;

    private static final int _ord_ID = 1;
    private static final int _ord_PROCESS_ID = _ord_ID + 4;
    private static final int _ord_SERIAL = _ord_PROCESS_ID + 1;

    @Id
    @NotNull
    long id;

    @NotNull
    String serial;

    /**  */
    @NotNull
    FabProcess process;

    @NotNull
    long processId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    @Ordinal(_ord_SERIAL)
    @NotNull
    @Precision(value = N_SERIAL)
    @TextInput(maxLength = N_SERIAL)
    @Column(name = "serial", nullable = false, length = N_SERIAL)
    public String getSerial() {
        return serial;
    }

    public void setSerial(@NotNull String value) {
        this.serial = value;
    }

    /**
     *
     * @label proc
     * @constraint foreign key (proc) references violet.fabproc (id)
     */
    @NotNull
    public FabProcess getProcess() {
        return process;
    }

    /**
     */
    public void setProcess(@NotNull FabProcess value) {
        this.process = value;
    }

    @Ordinal(_ord_PROCESS_ID)
    @Precision(value = 19)
    @Column(name = "proc", nullable = false, precision = 19)
    public synchronized long getProcessId() {
        if (process != null) {
            return process.getId();
        }
        return processId;
    }

    public synchronized void setProcessId(long value) {
        this.processId = value;
    }

    public void initNotNulls() {
        this.serial = "";
    }

}
