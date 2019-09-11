package net.bodz.bas.t.set;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.object.IdPool;
import net.bodz.bas.c.reflect.query.ReflectQuery;
import net.bodz.bas.repr.view.Tags;

public abstract class AbstractTaggedSetTest
        extends Assert {

    @Tags({ "red", "big", "sweet", "fruit" })
    String apple = "1apple";

    @Tags({ "white", "cold", "fruit" })
    String pear = "2pear";

    @Tags({ "yellow", "fruit", "long", "sweet", "big", "cute" })
    String banana = "3banana";

    @Tags({ "cute", "big", "friend", "white" })
    String dog = "4dog";

    @Tags({ "small", "sweet", "yellow", "moe", "cute" })
    String cat = "5cat";

    @Tags({ "sweet", "big", "hot", "food" })
    String cake = "6cake";

    @Tags({ "big", "red", "solid", "building", "cold", "white" })
    String house = "7house";

    protected Map<Object, String[]> itemMap;
    protected IdPool pool = IdPool.getInstance();

    public AbstractTaggedSetTest()
            throws ReflectiveOperationException {

        itemMap = new IdentityHashMap<Object, String[]>();
        for (Field field : ReflectQuery.selectDeclaredFields(getClass())) {
            if (field.isAnnotationPresent(Tags.class)) {
                field.setAccessible(true);
                Object item = field.get(this);
                String[] tags = field.getAnnotation(Tags.class).value();
                itemMap.put(item, tags);

                // FOR DEBUG, order items in sequence.
                // int id = pool.getId(item);
                // System.out.println("ID#" + item + " = " + id);
            }
        }
    }

    protected abstract <T> TaggedSet<T> createTaggedSet();

    protected TaggedSet<?> make() {
        TaggedSet<Object> taggedSet = createTaggedSet();
        for (Entry<?, String[]> entry : itemMap.entrySet())
            taggedSet.add(entry.getKey(), entry.getValue());
        return taggedSet;
    }

    @Test
    public void selectYellow() {
        TaggedSet<?> taggedSet = make();
        Collection<?> selection = taggedSet.selectForAll("yellow");
        assertEquals(2, selection.size());
        assertTrue(selection.contains(banana));
        assertTrue(selection.contains(cat));
    }

    @Test
    public void selectBigCute() {
        TaggedSet<?> taggedSet = make();
        Collection<?> selection = taggedSet.selectForAll("big", "cute");
        assertEquals(2, selection.size());
        assertTrue(selection.contains(banana));
        assertTrue(selection.contains(dog));
    }

    @Test
    public void selectFruits() {
        TaggedSet<?> taggedSet = make();
        Collection<?> selection = taggedSet.selectForAll("fruit");
        assertEquals(3, selection.size());
        assertTrue(selection.contains(apple));
        assertTrue(selection.contains(pear));
        assertTrue(selection.contains(banana));
    }

    @Test
    public void selectAllTags() {
        TaggedSet<String> taggedSet = createTaggedSet();
        Random rand = new Random(1L);
        int ntag = 10;
        int nobj = 10;
        for (int x = 0; x < 100; x++) {
            int s = rand.nextInt(nobj);
            int t = rand.nextInt(ntag);
            String obj = c2s('a' + s);
            String tag = c2s('A' + t);
            taggedSet.addTag(obj, tag);
        }
        String[] allTags = new String[ntag];
        for (int t = 0; t < ntag; t++) {
            String tag = c2s('A' + t);
            allTags[t] = tag;
            taggedSet.addTag("g", tag);
        }

        Collection<?> selection = taggedSet.selectForAll(allTags);
        assertTrue(selection.contains("g"));
    }

    static String c2s(int ch) {
        String s = String.valueOf((char) ch);
        return s.intern();
    }

}
