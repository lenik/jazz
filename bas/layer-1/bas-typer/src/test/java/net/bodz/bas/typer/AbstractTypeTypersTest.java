package net.bodz.bas.typer;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.meta.lang.TyperClass;
import net.bodz.bas.t.pojo.eg.Person;
import net.bodz.bas.t.pojo.eg.PersonTypers;
import net.bodz.bas.typer.std.IBasicTyperFamily;

@TyperClass(PersonTypers.class)
public class AbstractTypeTypersTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        IBasicTyperFamily<Person> typers = new PersonTypers();

        typers.getValidator().validate(Person.Lenik);
        String lucyFormat = typers.getFormatter().format(Person.Lucy);
        System.out.println("Lucy: " + lucyFormat);
    }

}
