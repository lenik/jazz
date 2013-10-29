package net.bodz.bas.t.pojo.eg;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.typer.std.IBasicTyperFamily;

public class PersonTypersTest
        extends Assert {

    @Test
    public void testFormat()
            throws Exception {
        IBasicTyperFamily<Person> family = new PersonTypers();

        family.getValidator().validate(Person.Lenik);

        String lucyFormat = family.getFormatter().format(Person.Lucy);

        /*
         * Lucy: <Person name=Lucy age=16 girl
         * location="200 Golf Road, New York, USA PO[23456] TEL[234-567-888]">
         */
        System.out.println("Lucy: " + lucyFormat);
    }

}
