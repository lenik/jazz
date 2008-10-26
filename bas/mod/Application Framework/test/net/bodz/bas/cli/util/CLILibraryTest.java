package net.bodz.bas.cli.util;

import static org.junit.Assert.*;
import net.bodz.bas.cli.CLIException;
import net.bodz.bas.test.types.Dog;

import org.junit.Test;

public class CLILibraryTest {

    CLILibrary lib = new CLILibrary();

    @Test
    public void test1() throws CLIException {
        Dog dog = new Dog("CC");
        lib.addMethods(dog);
        assertEquals("Wang wang!!", lib.eval("bark"));
        assertEquals("Wang wang!!", lib.eval("bark  "));

        assertEquals("tom", lib.eval("bark tom"));
        assertEquals("tom, lucy", lib.eval("bark   'tom, lucy'"));
        assertEquals("d'ni", lib.eval("bark \n 'd\\'ni'"));

        assertEquals("TL", lib.eval("bark 'Tom, Lucy' true"));
    }

}
