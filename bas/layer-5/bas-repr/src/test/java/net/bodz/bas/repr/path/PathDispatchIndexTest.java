package net.bodz.bas.repr.path;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import net.bodz.bas.repr.path.builtin.FieldPathDispatcher;
import net.bodz.bas.repr.path.builtin.ListPathDispatcher;
import net.bodz.bas.repr.path.builtin.MapPathDispatcher;
import net.bodz.bas.repr.path.builtin.MethodPathDispatcher;
import net.bodz.bas.repr.path.builtin.OverriddenPathDispatcher;
import net.bodz.bas.repr.path.builtin.PropertyPathDispatcher;
import net.bodz.bas.repr.path.builtin.TreeNodePathDispatcher;
import net.bodz.bas.t.tree.MapTreeNode;

public class PathDispatchIndexTest
        extends Assert
        implements
            IPathDispatcherHelper {

    @Test
    public void testLoadProviders() {
        PathDispatchIndex index = PathDispatchIndex.getInstance();

        Set<Class<?>> forMath = index.getDispatcherTypes(Math.class);
        Class<?>[] forObject = { //
                FieldPathDispatcher.class, //
                PropertyPathDispatcher.class, //
                MethodPathDispatcher.class, //
                OverriddenPathDispatcher.class //
        };
        for (Class<?> c : forObject)
            assertTrue(c.getName(), forMath.contains(c));

        Set<IPathDispatcher> forArrayList = index.getDispatchers(ArrayList.class);
        Class<?>[] forList = { ListPathDispatcher.class };
        for (Class<?> c : forList)
            assertTrue(c.getName(), forArrayList.contains(c));

        Set<IPathDispatcher> forLinkedHashMap = index.getDispatchers(LinkedHashMap.class);
        Class<?>[] forMap = { MapPathDispatcher.class };
        for (Class<?> c : forMap)
            assertTrue(c.getName(), forLinkedHashMap.contains(c));

        Set<IPathDispatcher> forMapTreeNode = index.getDispatchers(MapTreeNode.class);
        Class<?>[] forTreeNode = { TreeNodePathDispatcher.class };
        for (Class<?> c : forTreeNode)
            assertTrue(c.getName(), forMapTreeNode.contains(c));
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
        assertEquals("coridor", dispatchTest(service, home, "address", null));
        assertEquals("Punjab", dispatchTest(service, home, "city", null));
        assertEquals("very long", dispatchTest(service, home, "road", null));
    }

}
