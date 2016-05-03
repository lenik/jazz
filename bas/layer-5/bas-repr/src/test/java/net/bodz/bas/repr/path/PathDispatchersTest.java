package net.bodz.bas.repr.path;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.builtin.FieldPathDispatcher;
import net.bodz.bas.repr.path.builtin.MapPathDispatcher;
import net.bodz.bas.repr.path.builtin.NoPathRefDispatcher;
import net.bodz.bas.repr.path.builtin.OverriddenPathDispatcher;

public class PathDispatchersTest
        extends Assert {

    PathDispatchers cpd;
    Foo foo = new Foo();

    public PathDispatchersTest() {
        cpd = new PathDispatchers();
        cpd.addDispatcher(new FieldPathDispatcher());
        cpd.addDispatcher(new MapPathDispatcher());
        cpd.addDispatcher(new OverriddenPathDispatcher());
        cpd.addDispatcher(new NoPathRefDispatcher());
    }

    @Test
    public void testSelf()
            throws PathDispatchException {
        IPathArrival arrival = cpd.dispatch(foo, "bar");
        assertEquals("Bar", arrival.getTarget());
    }

    @Test
    public void testField()
            throws PathDispatchException {
        IPathArrival arrival = cpd.dispatch(foo, "cat");
        assertEquals("Cat", arrival.getTarget());
    }

    @Test
    public void testRef1()
            throws PathDispatchException {
        IPathArrival arrival = cpd.dispatch(foo, "name");
        assertEquals("Tom", arrival.getTarget());
    }

    @Test
    public void testRef2()
            throws PathDispatchException {
        IPathArrival arrival = cpd.dispatch(foo, "buz/name");
        assertEquals("Kate", arrival.getTarget());
    }

    public static class Foo
            implements IPathDispatchable, INoPathRef {

        public String cat = "Cat";
        private Map<String, String> map1 = new HashMap<>();
        private Map<String, String> map2 = new HashMap<>();

        public INoPathRef buz = NprWrapper.wrap(NprWrapper.wrap(map2));

        public Foo() {
            map1.put("name", "Tom");
            map2.put("name", "Kate");
        }

        @Override
        public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
                throws PathDispatchException {
            String token = tokens.peek();
            if (token == null)
                return null;

            switch (token) {
            case "bar":
                return PathArrival.shift(previous, "Bar", tokens);
            }
            return null;
        }

        @Override
        public Object getTarget() {
            return map1;
        }

        @Override
        public String toString() {
            return "A";
        }

    }

}
