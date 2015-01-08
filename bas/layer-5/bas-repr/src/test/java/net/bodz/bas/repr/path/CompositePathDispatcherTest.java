package net.bodz.bas.repr.path;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.fn.Constant;
import net.bodz.bas.fn.IEvaluable;
import net.bodz.bas.repr.path.builtin.FieldPathDispatcher;
import net.bodz.bas.repr.path.builtin.MapPathDispatcher;
import net.bodz.bas.repr.path.builtin.OverriddenPathDispatcher;

public class CompositePathDispatcherTest
        extends Assert {

    CompositePathDispatcher cpd;
    Foo foo = new Foo();

    public CompositePathDispatcherTest() {
        cpd = new CompositePathDispatcher();
        cpd.addDispatcher(new FieldPathDispatcher());
        cpd.addDispatcher(new MapPathDispatcher());
        cpd.addDispatcher(new OverriddenPathDispatcher());
        cpd.setMaxRefDepth(3);
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
            implements IPathDispatchable, IEvaluable<Object> {

        public String cat = "Cat";
        private Map<String, String> map1 = new HashMap<>();
        private Map<String, String> map2 = new HashMap<>();

        public Constant<?> buz = Constant.of(Constant.of(map2));

        public Foo() {
            map1.put("name", "Tom");
            map2.put("name", "Kate");
        }

        @Override
        public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
                throws PathDispatchException {
            switch (tokens.peek()) {
            case "bar":
                return PathArrival.shift(previous, "Bar", tokens);
            }
            return null;
        }

        @Override
        public Object eval() {
            return map1;
        }

        @Override
        public String toString() {
            return "A";
        }

    }

}
