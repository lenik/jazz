package net.bodz.bas.t.set;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.object.IdPool;
import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.repr.view.Feature;

public abstract class AbstractTaggedSetTest
        extends Assert {

    @Feature({ "red", "big", "sweet", "fruit" })
    String apple = "1apple";

    @Feature({ "white", "cold", "fruit" })
    String pear = "2pear";

    @Feature({ "yellow", "fruit", "long", "sweet", "big", "cute" })
    String banana = "3banana";

    @Feature({ "cute", "big", "friend", "white" })
    String dog = "4dog";

    @Feature({ "small", "sweet", "yellow", "moe", "cute" })
    String cat = "5cat";

    @Feature({ "sweet", "big", "hot", "food" })
    String cake = "6cake";

    @Feature({ "big", "red", "solid", "building", "cold", "white" })
    String house = "7house";

    protected Map<Object, String[]> itemMap;
    protected IdPool pool = IdPool.getInstance();

    public AbstractTaggedSetTest()
            throws ReflectiveOperationException {

        itemMap = new IdentityHashMap<>();
        for (Field field : ReflectQuery.selectDeclaredFields(getClass())) {
            if (field.isAnnotationPresent(Feature.class)) {
                field.setAccessible(true);
                Object item = field.get(this);
                String[] tags = field.getAnnotation(Feature.class).value();
                itemMap.put(item, tags);

                // FOR DEBUG, order items in sequence.
                // int id = pool.getId(item);
                // System.out.println("ID#" + item + " = " + id);
            }
        }
    }

    protected abstract TaggedSet<Object> createTaggedSet();

    protected TaggedSet<?> make() {
        TaggedSet<Object> taggedSet = createTaggedSet();
        for (Entry<?, String[]> entry : itemMap.entrySet())
            taggedSet.add(entry.getKey(), entry.getValue());
        return taggedSet;
    }

    @Test
    public void selectYellow() {
        TaggedSet<?> taggedSet = make();
        Collection<?> selection = taggedSet.select("yellow");
        assertEquals(2, selection.size());
        assertTrue(selection.contains(banana));
        assertTrue(selection.contains(cat));
    }

    @Test
    public void selectBigCute() {
        TaggedSet<?> taggedSet = make();
        Collection<?> selection = taggedSet.select("big", "cute");
        assertEquals(2, selection.size());
        assertTrue(selection.contains(banana));
        assertTrue(selection.contains(dog));
    }

}
