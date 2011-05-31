package net.bodz.bas.traits;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.meta.lang.TraitsClass;
import net.bodz.bas.util.example.Person;
import net.bodz.bas.util.example.PersonTraits;

@TraitsClass(PersonTraits.class)
public class AbstractTypeTraitsTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        ICommonTraits<Person> traits = new PersonTraits();

        traits.getValidator().validate(Person.Lenik);
        String lucyFormat = traits.getFormatter().format(Person.Lucy);
        System.out.println("Lucy: " + lucyFormat);
    }

}
