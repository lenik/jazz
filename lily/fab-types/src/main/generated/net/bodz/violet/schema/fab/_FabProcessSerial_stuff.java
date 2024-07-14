package net.bodz.violet.schema.fab;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _FabProcessSerial_stuff
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabproc_sn";

    public static final String FIELD_PROCESS_ID = "proc";
    public static final String FIELD_SERIAL = "serial";

    public static final int N_PROCESS_ID = 19;
    public static final int N_SERIAL = 40;

    private static final int _ord_PROCESS_ID = 5;
    private static final int _ord_SERIAL = _ord_PROCESS_ID + 1;

    @NotNull
    String serial;

    /**  */
    @NotNull
    FabProcess process;

    @NotNull
    long processId;

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
     * @constraint foreign key (proc) references violet.fabproc (id)
     */
    @JoinColumn(name = "proc")
    @ManyToOne
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
            if (process.getId() == null)
                return 0L;
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
