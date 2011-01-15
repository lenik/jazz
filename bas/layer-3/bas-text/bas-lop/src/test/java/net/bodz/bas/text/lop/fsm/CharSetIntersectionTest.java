package net.bodz.bas.text.lop.fsm;

import org.junit.Assert;
import org.junit.Test;

public class CharSetIntersectionTest
        extends Assert
        implements TestData {

    @Test
    public void test()
            throws Exception {
        CharSetIntersection s = new CharSetIntersection(_3to7, _5to9);

        assert !s.contains('4');
        assert s.contains('5');
        assert s.contains('6');
        assert s.contains('7');
        assert !s.contains('8');

        CharSet reduced = s.reduce();
        System.out.println("Reduced: " + reduced);
    }

}
