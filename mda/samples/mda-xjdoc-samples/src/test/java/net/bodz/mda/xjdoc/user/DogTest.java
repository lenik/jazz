package net.bodz.mda.xjdoc.user;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import user.xjdoc.pojo.Dog;

public class DogTest
        extends Assert {

    @Test
    public void testGreeting()
            throws IOException {
        Dog dog = new Dog();
        dog.greet("fool", "ever");
    }

}
