package net.bodz.violet.asset;

import javax.persistence.Table;

import net.bodz.lily.contact.Person;

@Table(name = "asset_person")
public class PersonAsset
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    Person owner;

    public PersonAsset() {
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

}
