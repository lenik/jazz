package net.bodz.violet.schema.edu;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.contact.Person;

@IdType(Long.class)
public abstract class _TestApply_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "testapply";

    public static final String FIELD_ID = "id";
    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";
    public static final String FIELD_YEAR = "year";
    public static final String FIELD_PAPER_ID = "paper";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_SCORE = "score";

    public static final int N_ID = 19;
    public static final int N_BEGIN_TIME = 35;
    public static final int N_END_TIME = 35;
    public static final int N_YEAR = 10;
    public static final int N_PAPER_ID = 10;
    public static final int N_PERSON_ID = 10;
    public static final int N_SCORE = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_BEGIN_TIME = 14;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_PAPER_ID = _ord_YEAR + 1;
    private static final int _ord_PERSON_ID = _ord_PAPER_ID + 1;
    private static final int _ord_SCORE = _ord_PERSON_ID + 1;

    @Id
    @NotNull
    long id;

    ZonedDateTime beginTime;

    ZonedDateTime endTime;

    @NotNull
    int year;

    BigDecimal score;

    /**  */
    Person person;

    Integer personId;

    /**  */
    TestPaper paper;

    Integer paperId;

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

    @Ordinal(_ord_BEGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t0", precision = 35, scale = 6)
    public ZonedDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(ZonedDateTime value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime value) {
        this.endTime = value;
    }

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

    @Ordinal(_ord_SCORE)
    @Precision(value = N_SCORE, scale = 2)
    @Column(name = "score", precision = 10, scale = 2)
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal value) {
        this.score = value;
    }

    /**
     *
     * @label person
     * @constraint foreign key (person) references lily.person (id)
     */
    public Person getPerson() {
        return person;
    }

    /**
     */
    public void setPerson(Person value) {
        this.person = value;
    }

    @Ordinal(_ord_PERSON_ID)
    @Precision(value = N_PERSON_ID)
    @Column(name = "person", precision = 10)
    public synchronized Integer getPersonId() {
        if (person != null) {
            return person.getId();
        }
        return personId;
    }

    public synchronized void setPersonId(Integer value) {
        this.personId = value;
    }

    /**
     *
     * @label paper
     * @constraint foreign key (paper) references violet.testpaper (id)
     */
    public TestPaper getPaper() {
        return paper;
    }

    /**
     */
    public void setPaper(TestPaper value) {
        this.paper = value;
    }

    @Ordinal(_ord_PAPER_ID)
    @Precision(value = N_PAPER_ID)
    @Column(name = "paper", precision = 10)
    public synchronized Integer getPaperId() {
        if (paper != null) {
            return paper.getId();
        }
        return paperId;
    }

    public synchronized void setPaperId(Integer value) {
        this.paperId = value;
    }

    public void initNotNulls() {
    }

}
