package net.bodz.bas.sugar;

import net.bodz.bas.util.example.Person;

import org.junit.Assert;
import org.junit.Test;

public class ToolingTest
        extends Assert {

    @Test
    public void testGreeting() {
        Boy boy = new Boy();
        boy.setName("tom");
        String mesg = boy.tooling()._for(Greeting.class).greet();
        assertEquals("hello, tom", mesg);
    }

    public static class Boy
            extends Person
            implements IToolable {

        @Override
        public Tooling tooling() {
            return new Tooling(this);
        }

    }

    public static class Greeting {

        Person person;

        public Greeting(Person person) {
            this.person = person;
        }

        public String greet() {
            return "hello, " + person.getName();
        }
    }

}
