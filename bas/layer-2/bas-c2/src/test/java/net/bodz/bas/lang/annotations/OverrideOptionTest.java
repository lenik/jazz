package net.bodz.bas.lang.annotations;

import net.bodz.bas.annotation.util.Annotations;
import net.bodz.bas.hint.OverrideOption;

import org.junit.Test;

public class OverrideOptionTest {

    /** @see net.bodz.bas.functors.Functor2#validate */
    @Test
    public void testA() {
        OverrideOption ovropt = Annotations.getMethodAnnotation(_Functor.class,
                new MethodSignature("validate"), OverrideOption.class); 
        if (ovropt != null) {
            System.out.println("chain = " + ovropt.chain()); 
            System.out.println("ordr = " + ovropt.order()); 
        } else {
            System.out.println("no override option"); 
        }
    }

}
