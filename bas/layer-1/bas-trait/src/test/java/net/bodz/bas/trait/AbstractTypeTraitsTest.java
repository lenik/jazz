package net.bodz.bas.trait;

import net.bodz.bas.meta.lang.TraitClass;
import net.bodz.bas.traits.ICommonTraits;
import net.bodz.bas.util.example.Person;
import net.bodz.bas.util.example.PersonTraits;

import org.junit.Assert;
import org.junit.Test;

@TraitClass(PersonTraits.class)
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
