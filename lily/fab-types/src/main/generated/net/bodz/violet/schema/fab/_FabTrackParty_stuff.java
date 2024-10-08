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
import net.bodz.lily.schema.contact.Person;

@IdType(Long.class)
public abstract class _FabTrackParty_stuff
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtrack_op";

    public static final String FIELD_TRACK_ID = "track";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_ROLE = "role";

    public static final int N_TRACK_ID = 19;
    public static final int N_PERSON_ID = 10;
    public static final int N_ROLE = 30;

    private static final int _ord_TRACK_ID = 5;
    private static final int _ord_PERSON_ID = _ord_TRACK_ID + 1;
    private static final int _ord_ROLE = _ord_PERSON_ID + 1;

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

    /**
     *
     * @constraint foreign key (track) references violet.fabtrack (id)
     */
    @JoinColumn(name = "track")
    @ManyToOne
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
            if (track.getId() == null)
                return 0L;
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
