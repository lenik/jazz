package net.bodz.bas.fmt.rst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.fmt.rst.obj.ReflectRstDumper;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.StringSource;

import user.zoo.reflect.Cat;
import user.zoo.reflect.Dog;
import user.zoo.reflect.Zoo;

public class ReflectRstDumperTest
        extends Assert {

    Zoo zoo = new Zoo();

    @Before
    public void setup() {
        zoo.owner = "Lenik";
        zoo.createdYear = 2013;
        zoo.cats.add(new Cat("Tom", true, 3, 6, 'o'));
        zoo.cats.add(new Cat("Cafe", true, 13, 5, 'k'));
        zoo.dogs.add(new Dog("Odi", true, 15, 2, 30));
        zoo.dogs.add(new Dog("Donald", false, 15, 3, 32));
    }

    @Test
    public void test1()
            throws Exception {
        String rst = RstFn.toString(zoo);

        Zoo zoo2 = new Zoo();

        RstLoader loader = new RstLoader();
        loader.load(new StringSource(rst), zoo2);

        IRstOutput out = RstOutputImpl.from(Stdio.cout);
        new ReflectRstDumper(out).dump(zoo2);
    }

}
