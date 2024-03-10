package net.bodz.violet.schema.edu;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _TestQuestionFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "testq_fav";

    public static final String FIELD_TESTQ_ID = "testq";

    public static final int N_TESTQ_ID = 19;

    private static final int _ord_TESTQ_ID = 2;

    /**  */
    @NotNull
    TestQuestion testq;

    @NotNull
    long testqId;

    /**
     *
     * @constraint foreign key (testq) references violet.testq (id)
     */
    @JoinColumn(name = "testq")
    @ManyToOne
    @NotNull
    public TestQuestion getTestq() {
        return testq;
    }

    /**
     */
    public void setTestq(@NotNull TestQuestion value) {
        this.testq = value;
    }

    @Ordinal(_ord_TESTQ_ID)
    @Precision(value = 19)
    @Column(name = "testq", nullable = false, precision = 19)
    public synchronized long getTestqId() {
        if (testq != null) {
            if (testq.getId() == null)
                return 0L;
            return testq.getId();
        }
        return testqId;
    }

    public synchronized void setTestqId(long value) {
        this.testqId = value;
    }

    public void initNotNulls() {
    }

}
