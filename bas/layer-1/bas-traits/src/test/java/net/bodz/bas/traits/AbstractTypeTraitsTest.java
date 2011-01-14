package net.bodz.bas.traits;

import java.util.Map;

import junit.framework.TestCase;
import net.bodz.bas.meta.lang.TraitsClass;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.ICommonTraits;
import net.bodz.bas.util.example.Person;
import net.bodz.bas.util.example.PersonTraits;

import org.junit.Test;

@TraitsClass(PersonTraits.class)
public class AbstractTypeTraitsTest
        extends TestCase {

    @Test
    public void test1()
            throws Exception {
        ICommonTraits<Person> traits = new PersonTraits();

        Map<String, Object> implMap = ((AbstractCommonTraits<?>) traits).getImplementationMap();
        System.out.println(implMap);

        traits.getValidator().validate(Person.Lenik);
        String lucyFormat = traits.getFormatter().format(Person.Lucy);
        System.out.println("Lucy: " + lucyFormat);
    }

}
