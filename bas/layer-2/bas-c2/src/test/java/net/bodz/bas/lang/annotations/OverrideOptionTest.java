package net.bodz.bas.lang.annotations;

import net.bodz.bas.commons.scripting.MethodSignature;
import net.bodz.bas.commons.util.Annotations;
import net.bodz.bas.hint.OverrideOption;
import net.bodz.bas.lang.functors._Functor;

import org.junit.Test;

public class OverrideOptionTest {

    /** @see net.bodz.bas.functors.Functor2#validate */
    @Test
    public void testA() {
        OverrideOption ovropt = Annotations.getMethodAnnotation(_Functor.class,
                new MethodSignature("validate"), OverrideOption.class); //$NON-NLS-1$
        if (ovropt != null) {
            System.out.println("chain = " + ovropt.chain()); //$NON-NLS-1$
            System.out.println("ordr = " + ovropt.order()); //$NON-NLS-1$
        } else {
            System.out.println("no override option"); //$NON-NLS-1$
        }
    }

}
