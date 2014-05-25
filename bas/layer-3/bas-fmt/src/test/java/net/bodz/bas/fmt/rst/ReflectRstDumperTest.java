package net.bodz.bas.fmt.rst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.fmt.rst.ReflectRstDumper;
import net.bodz.bas.fmt.rst.RstLoader;
import net.bodz.bas.fmt.rst.RstOutputImpl;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.StringSource;

import user.zoo.Cat;
import user.zoo.Dog;
import user.zoo.Zoo;

public class ReflectRstDumperTest
        extends Assert {

    ReflectRstDumper dumper = ReflectRstDumper.getInstance();
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
        String rst = dumper.dump(zoo);

        Zoo zoo2 = new Zoo();

        RstLoader loader = new RstLoader();
        loader.load(new StringSource(rst), zoo2);

        IRstOutput out = RstOutputImpl.from(Stdio.cout);
        dumper.dump(out, zoo2);
    }

}