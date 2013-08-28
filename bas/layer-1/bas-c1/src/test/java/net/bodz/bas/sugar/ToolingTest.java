package net.bodz.bas.sugar;

import org.junit.Assert;
import org.junit.Test;

public class ToolingTest
        extends Assert {

    @Test
    public void testGreeting() {
        Alien alien = new Alien("tom");
        String mesg = alien.tooling()._for(Greeting.class).greet();
        assertEquals("hello, tom", mesg);
    }

    public static class Alien
            implements IToolable {

        String name;

        public Alien(String name) {
            if (name == null)
                throw new NullPointerException("name");
            this.name = name;
        }

        @Override
        public Tooling tooling() {
            return new Tooling(this);
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public static class Greeting {

        Object guest;

        public Greeting(Object guest) {
            this.guest = guest;
        }

        public String greet() {
            return "hello, " + guest;
        }
    }

}
