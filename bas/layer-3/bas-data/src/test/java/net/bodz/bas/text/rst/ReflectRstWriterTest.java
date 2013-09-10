package net.bodz.bas.text.rst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.builtin.StringSource;
import net.bodz.bas.t.pojo.eg.bookstore.Cat;
import net.bodz.bas.t.pojo.eg.bookstore.Dog;
import net.bodz.bas.t.pojo.eg.bookstore.Zoo;

public class ReflectRstWriterTest
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
        BCharOut buf = new BCharOut();
        IRstOutput out = RstOutputImpl.from(buf);
        ReflectRstWriter.writeObject(out, zoo);

        String rst = buf.toString();

        RstLoader loader = new RstLoader();
        Zoo zoo2 = new Zoo();
        loader.load(new StringSource(rst), zoo2);

        out = RstOutputImpl.from(Stdio.cout);
        ReflectRstWriter.writeObject(out, zoo2);
    }

}
