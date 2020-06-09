package net.bodz.violet.fab;

import javax.persistence.Table;

import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "fabtrack_op")
@IdType(Long.class)
public class FabTrackParty
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    FabTrack track;
    Person person;
    String role;

    public FabTrackParty() {
    }

    public FabTrack getTrack() {
        return track;
    }

    public void setTrack(FabTrack track) {
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
