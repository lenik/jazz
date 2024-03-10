package net.bodz.violet.schema.edu;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.contact.Person;

@IdType(Long.class)
public abstract class _TestApply_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "testapply";

    public static final String FIELD_PAPER_ID = "paper";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_SCORE = "score";

    public static final int N_PAPER_ID = 10;
    public static final int N_PERSON_ID = 10;
    public static final int N_SCORE = 10;

    private static final int _ord_PAPER_ID = 18;
    private static final int _ord_PERSON_ID = _ord_PAPER_ID + 1;
    private static final int _ord_SCORE = _ord_PERSON_ID + 1;

    BigDecimal score;

    /**  */
    Person person;

    Integer personId;

    /**  */
    TestPaper paper;

    Integer paperId;

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
     * @constraint foreign key (person) references lily.person (id)
     */
    @JoinColumn(name = "person")
    @ManyToOne
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
     * @constraint foreign key (paper) references violet.testpaper (id)
     */
    @JoinColumn(name = "paper")
    @ManyToOne
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
