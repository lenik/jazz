package net.bodz.bas.types;

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.bodz.bas.commons.collection.hierarchical.TypeHierSet;
import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class TypeHierSetTest {

    @Test
    public void test1() {
        TypeHierSet set = new TypeHierSet();
        set.add(Number.class);
        set.add(Object.class);
        set.add(String.class);
        System.out.println(Strings.join(", ", set)); //$NON-NLS-1$
        assertEquals(Number.class, set.floor(Float.class));
        assertEquals(Object.class, set.floor(List.class));

        set.add(Double.class);
        set.add(Integer.class);
        System.out.println(Strings.join(", ", set)); //$NON-NLS-1$
        assertEquals(Number.class, set.floor(Float.class));
        assertEquals(Object.class, set.floor(List.class));
    }

}
