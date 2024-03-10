package net.bodz.lily.schema.contact;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _PersonTag_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "person_tag";

    public static final String FIELD_ID = "id";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_TAG_ID = "tag";

    public static final int N_ID = 10;
    public static final int N_PERSON_ID = 10;
    public static final int N_TAG_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_PERSON_ID = _ord_ID + 4;
    private static final int _ord_TAG_ID = _ord_PERSON_ID + 1;

    @Id
    @NotNull
    int id;

    /**  */
    @NotNull
    PersonTagType tag;

    @NotNull
    int tagId;

    /**  */
    @NotNull
    Person person;

    @NotNull
    int personId;

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

    /**
     *
     * @constraint foreign key (tag) references lily.persontag (id)
     */
    @JoinColumn(name = "tag")
    @ManyToOne
    @NotNull
    public PersonTagType getTag() {
        return tag;
    }

    /**
     */
    public void setTag(@NotNull PersonTagType value) {
        this.tag = value;
    }

    @Ordinal(_ord_TAG_ID)
    @Precision(value = 10)
    @Column(name = "tag", nullable = false, precision = 10)
    public synchronized int getTagId() {
        if (tag != null) {
            if (tag.getId() == null)
                return 0;
            return tag.getId();
        }
        return tagId;
    }

    public synchronized void setTagId(int value) {
        this.tagId = value;
    }

    /**
     *
     * @constraint foreign key (person) references lily.person (id)
     */
    @JoinColumn(name = "person")
    @ManyToOne
    @NotNull
    public Person getPerson() {
        return person;
    }

    /**
     */
    public void setPerson(@NotNull Person value) {
        this.person = value;
    }

    @Ordinal(_ord_PERSON_ID)
    @Precision(value = 10)
    @Column(name = "person", nullable = false, precision = 10)
    public synchronized int getPersonId() {
        if (person != null) {
            if (person.getId() == null)
                return 0;
            return person.getId();
        }
        return personId;
    }

    public synchronized void setPersonId(int value) {
        this.personId = value;
    }

    public void initNotNulls() {
    }

}
