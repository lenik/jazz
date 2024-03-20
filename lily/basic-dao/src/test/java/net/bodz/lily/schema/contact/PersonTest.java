package net.bodz.lily.schema.contact;

import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.lily.schema.contact.Gender;
import net.bodz.lily.schema.contact.Person;

public class PersonTest {

    public static void main(String[] args)
            throws Exception {
        Person p = new Person();
        p.setAge(20);
        p.setLabel("Tom");
        p.setGender(Gender.MALE);
        String json = JsonFn.toJson(p);
        System.out.println(json);
    }
}