package net.bodz.mda.xjdoc.user;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

import user.xjdoc.pojo.Dog;

public class DogTest
        extends Assert {

    @Test
    public void testGreeting()
            throws IOException {
        Dog dog = new Dog();
        dog.greet("fool", "ever");
    }

    @Test
    public void testClassDoc() {
        ClassDoc doc = Xjdocs.getDefaultProvider().getClassDoc(Dog.class);
        System.out.println("classDoc:");
        System.out.println(doc.getText().toMultiLangString());
        System.out.println("weight: " + doc.getString("weight"));
    }

}
