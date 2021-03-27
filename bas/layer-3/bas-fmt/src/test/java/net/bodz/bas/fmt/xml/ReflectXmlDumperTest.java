package net.bodz.bas.fmt.xml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.fmt.xml.obj.ReflectXmlDumper;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.StringSource;

import user.zoo.reflect.Cat;
import user.zoo.reflect.Dog;
import user.zoo.reflect.Zoo;

public class ReflectXmlDumperTest
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
        String xml = XmlFn.toString(zoo);

        Zoo zoo2 = new Zoo();

        XmlLoader.load(zoo2, new StringSource(xml));

        IXmlOutput out = XmlOutputImpl.from(Stdio.cout);
        new ReflectXmlDumper(out).dump(zoo2);
    }

}
