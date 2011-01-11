package net.bodz.bas.type;

import java.util.Map;

import junit.framework.TestCase;
import net.bodz.bas.c1.examples.Person;

import org.junit.Test;

@TypeTraitsBy(PersonTraits.class)
public class AbstractTypeTraitsTest
        extends TestCase {

    @Test
    public void test1()
            throws Exception {
        ITypeTraits<Person> traits = new PersonTraits();

        Map<String, Object> implMap = ((AbstractTypeTraits<?>) traits).getImplementationMap();
        System.out.println(implMap);

        traits.getValidator().validate(Person.Lenik);
        String lucyFormat = traits.getFormatter().format(Person.Lucy);
        System.out.println("Lucy: " + lucyFormat);
    }

}
