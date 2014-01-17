package net.bodz.bas.repr.path;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.builtin.*;

public class PathDispatchServiceTest
        extends Assert {

    @Test
    public void testLoadProviders() {
        Set<Class<?>> dispatcherClasses = new HashSet<Class<?>>();
        for (IPathDispatcher dispatcher : PathDispatchService.getInstance().getDispatchers()) {
            // System.out.println(dispatcher);
            dispatcherClasses.add(dispatcher.getClass());
        }

        Class<?>[] classes = {
                //
                ListPathDispatcher.class, //
                MapPathDispatcher.class, //
                TreeNodePathDispatcher.class, //
                FieldPathDispatcher.class, //
                PropertyPathDispatcher.class, //
                MethodPathDispatcher.class, //
                OverriddenPathDispatcher.class //
        };
        for (Class<?> c : classes) {
            assertTrue(c.getName(), dispatcherClasses.contains(c));
        }
    }

    public class Home {
        public String address = "coridor";

        public String getCity() {
            return "Punjab";
        }

        public String road() {
            return "very long";
        }
    }

    @Test
    public void testMixed()
            throws PathDispatchException {
        Home home = new Home();
        PathDispatchService service = PathDispatchService.getInstance();
        assertEquals("coridor", service.dispatchTest(home, "address"));
        assertEquals("Punjab", service.dispatchTest(home, "city"));
        assertEquals("very long", service.dispatchTest(home, "road"));
    }

}
