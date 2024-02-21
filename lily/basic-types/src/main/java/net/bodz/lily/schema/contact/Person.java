package net.bodz.lily.schema.contact;

import java.util.List;

import javax.persistence.Table;

@Table(schema = Person.SCHEMA_NAME, name = Person.TABLE_NAME)
public class Person
        extends _Person_stuff {

    private static final long serialVersionUID = 1L;

    public String getHello() {
        return "world";
    }

    public List<String> getPeers() {
        return null;
    }

}
