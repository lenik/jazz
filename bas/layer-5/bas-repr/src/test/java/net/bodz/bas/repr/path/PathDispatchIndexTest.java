package net.bodz.bas.repr.path;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import net.bodz.bas.repr.path.builtin.*;

public class PathDispatchIndexTest
        extends Assert {

    @Test
    public void testLoadProviders() {
        Set<Class<?>> dispatcherClasses = new HashSet<Class<?>>();
        for (IPathDispatcher dispatcher : PathDispatchIndex.getInstance().getDispatchers()) {
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

    @Ignore
    @Test
    public void testMixed()
            throws PathDispatchException {
        Home home = new Home();
        PathDispatchIndex service = PathDispatchIndex.getInstance();
        assertEquals("coridor", service.dispatchTest(home, "address", null));
        assertEquals("Punjab", service.dispatchTest(home, "city", null));
        assertEquals("very long", service.dispatchTest(home, "road", null));
    }

}
