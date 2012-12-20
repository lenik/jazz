package net.bodz.bas.trait;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.meta.lang.TraitClass;
import net.bodz.bas.t.pojo.eg.Person;
import net.bodz.bas.t.pojo.eg.PersonTraits;
import net.bodz.bas.traits.ICommonTraits;

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
