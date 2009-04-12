package net.bodz.bas.cli.util;

import static org.junit.Assert.*;
import net.bodz.bas.cli.CLIException;
import net.bodz.bas.test.types.Dog;

import org.junit.Test;

@Deprecated
public class CLILibraryTest {

    CLILibrary lib = new CLILibrary();

    @Test
    public void test1() throws CLIException {
        Dog dog = new Dog("CC"); //$NON-NLS-1$
        lib.addMethods(dog);
        assertEquals("Wang wang!!", lib.eval("bark")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("Wang wang!!", lib.eval("bark  ")); //$NON-NLS-1$ //$NON-NLS-2$

        assertEquals("tom", lib.eval("bark tom")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("tom, lucy", lib.eval("bark   'tom, lucy'")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("d'ni", lib.eval("bark \n 'd\\'ni'")); //$NON-NLS-1$ //$NON-NLS-2$

        assertEquals("TL", lib.eval("bark 'Tom, Lucy' true")); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
