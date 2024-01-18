package net.bodz.violet.fab;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _FabTrackParty_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtrack_op";

    public static final String FIELD_ID = "id";
    public static final String FIELD_TRACK_ID = "track";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_ROLE = "role";

    public static final int N_ID = 19;
    public static final int N_TRACK_ID = 19;
    public static final int N_PERSON_ID = 10;
    public static final int N_ROLE = 30;

    private static final int _ord_ID = 1;
    private static final int _ord_TRACK_ID = _ord_ID + 4;
    private static final int _ord_PERSON_ID = _ord_TRACK_ID + 1;
    private static final int _ord_ROLE = _ord_PERSON_ID + 1;

    @Id
    @NotNull
    long id;

    String role;

    /**  */
    @NotNull
    Person person;

    @NotNull
    int personId;

    /**  */
    @NotNull
    FabTrack track;

    @NotNull
    long trackId;

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

    @Ordinal(_ord_ROLE)
    @Precision(value = N_ROLE)
    @TextInput(maxLength = N_ROLE)
    @Column(name = "role", length = N_ROLE)
    public String getRole() {
        return role;
    }

    public void setRole(String value) {
        this.role = value;
    }

    /**
     *
     * @label person
     * @constraint foreign key (person) references lily.person (id)
     */
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

    /**
     *
     * @label track
     * @constraint foreign key (track) references violet.fabtrack (id)
     */
    @NotNull
    public FabTrack getTrack() {
        return track;
    }

    /**
     */
    public void setTrack(@NotNull FabTrack value) {
        this.track = value;
    }

    @Ordinal(_ord_TRACK_ID)
    @Precision(value = 19)
    @Column(name = "track", nullable = false, precision = 19)
    public synchronized long getTrackId() {
        if (track != null) {
            return track.getId();
        }
        return trackId;
    }

    public synchronized void setTrackId(long value) {
        this.trackId = value;
    }

    public void initNotNulls() {
    }

}
