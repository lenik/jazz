package net.bodz.lily.entity.type;

import java.util.Arrays;

import org.junit.Test;

public class DefaultEntityTypeInfoTest {

    @Test
    public void test() {
    }

    public static void main(String[] args)
            throws Exception {
        IEntityTypeInfo info = EntityTypes.getTypeInfo(Foo.class);
        Object[] vals = info.parseIdColumns("tom", "13");
        System.out.println(Arrays.asList(vals));
        FooId id = (FooId) info.newId(vals);
        System.out.println(id);
    }

}
