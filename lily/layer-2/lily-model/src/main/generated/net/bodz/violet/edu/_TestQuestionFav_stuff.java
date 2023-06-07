package net.bodz.violet.edu;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.FavRecord;

@IdType(Long.class)
public abstract class _TestQuestionFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    private static final int _ord_TESTQ_ID = 2;

    /**  */
    @NotNull
    TestQuestion testq;

    @NotNull
    long testqId;

    /**
     *
     * @label testq
     * @constraint foreign key (testq) references violet.testq (id)
     */
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
