package net.bodz.bas.fmt.rst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.fmt.rst.obj.BeanRstDumper;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.StringSource;

import user.zoo.bean.Cat;
import user.zoo.bean.Dog;
import user.zoo.bean.Zoo;

public class BeanRstDumperTest
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
        String rst = RstFn.toString(zoo);
        System.out.println(rst);

        Zoo zoo2 = new Zoo();

        RstLoader loader = new RstLoader();
        loader.load(new StringSource(rst), zoo2);

        IRstOutput out = RstOutputImpl.from(Stdio.cout);
        new BeanRstDumper(out).dump(zoo2);
    }

}
