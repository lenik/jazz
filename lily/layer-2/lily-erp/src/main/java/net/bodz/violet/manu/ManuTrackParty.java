package net.bodz.violet.manu;

import javax.persistence.Table;

import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "manutrack_op")
@IdType(Long.class)
public class ManuTrackParty
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    ManuTrack track;
    Person person;
    String role;

    public ManuTrackParty() {
    }

    public ManuTrack getTrack() {
        return track;
    }

    public void setTrack(ManuTrack track) {
        this.track = track;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
