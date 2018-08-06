package net.bodz.lily.contact;

import net.bodz.lily.test.TestSamples;

public class PersonSamples
        extends TestSamples {

    public static Person build() {
        Person a = new Person();
        a.setLabel("person-1");
        a.setDescription("A person named person-1.");
        return a;
    }

}
