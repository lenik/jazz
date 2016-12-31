package net.bodz.bas.fmt.json;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.fmt.json.obj.BeanJsonDumper;

import user.zoo.bean.Cat;
import user.zoo.bean.Dog;
import user.zoo.bean.Zoo;

public class BeanJsonDumperTest
        extends Assert {

    Zoo zoo = new Zoo();

    @Before
    public void setup() {
        zoo.setOwner("Lenik");
        zoo.setCreatedYear(2013);
        zoo.getCats().add(new Cat("Tom", true, 3, 6, 'o'));
        zoo.getCats().add(new Cat("Cafe", true, 13, 5, 'k'));
        zoo.getDogs().add(new Dog("Odi", true, 15, 2, 30));
        zoo.getDogs().add(new Dog("Donald", false, 15, 3, 32));
    }

    @Test
    public void test1()
            throws Exception {
        String json = BeanJsonDumper.toString(zoo);
        System.out.println(json);
    }

}
