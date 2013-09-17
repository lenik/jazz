package net.bodz.bas.t.pojo.eg;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.meta.lang.typer;
import net.bodz.bas.typer.std.IBasicTyperFamily;

@typer.family(PersonTypers.class)
public class PersonTypersTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        IBasicTyperFamily<Person> family = new PersonTypers();

        family.getValidator().validate(Person.Lenik);
        String lucyFormat = family.getFormatter().format(Person.Lucy);
        System.out.println("Lucy: " + lucyFormat);
    }

}
