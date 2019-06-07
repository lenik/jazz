package net.bodz.violet.plan;

import java.io.IOException;

import javax.persistence.Table;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdAwares;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.security.User;

@Table(name = "diary_party")
@IdType(Long.class)
public class DiaryParty
        extends CoEntity<Long>
        implements IJsonSerializable {

    private static final long serialVersionUID = 1L;

    Diary diary;
    User user;
    Person person;
    Organization org;
    String role;
    int value;

    public DiaryParty() {
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != DiaryParty.class)
            return false;
        return partialEquals((DiaryParty) obj);
    }

    public boolean partialEquals(DiaryParty o) {
        if (value != o.value)
            return false;
        if (!IdAwares._nullable.idEquals(diary, o.diary))
            return false;
        if (!IdAwares._nullable.idEquals(user, o.user))
            return false;
        if (!IdAwares._nullable.idEquals(person, o.person))
            return false;
        if (!IdAwares._nullable.idEquals(org, o.org))
            return false;
        return super.partialEquals(o);
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        throw new NotImplementedException();
    }

    public void writeObject2(IJsonOut out)
            throws IOException {
        out.object();

        out.entryNotNull("id", getId());
        out.entryNotNull("label", getLabel());
        out.entryNotNull("description", getDescription());

        out.entry("value", getValue());

        if (diary != null) {
            out.key("diary");
            out.object();
            out.entry("id", diary.getId());
            out.endObject();
        }

        if (user != null) {
            out.key("user");
            out.object();
            out.entry("id", user.getId());
            out.entryNotNull("label", user.getLabel());
            out.entryNotNull("properties", user.getProperties());
            out.endObject();
        }
        if (person != null) {
            out.key("person");
            out.object();
            out.entry("id", person.getId());
            out.entryNotNull("label", person.getLabel());
            out.entryNotNull("properties", person.getProperties());
            out.endObject();
        }
        if (org != null) {
            out.key("org");
            out.object();
            out.entry("id", org.getId());
            out.entryNotNull("label", org.getLabel());
            out.entryNotNull("properties", org.getProperties());
            out.endObject();
        }
        out.endObject();
    }

}
